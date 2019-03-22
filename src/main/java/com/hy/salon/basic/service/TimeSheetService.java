package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.StuffVo;
import com.hy.salon.basic.vo.TimeSheetVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthTableUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Transactional(rollbackFor = Exception.class)
@Component("timeSheetService")
public class TimeSheetService {
    @Resource(name = "salonDao")
    private SalonDao salonDao;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    @Resource(name = "timeSheetDao")
    private TimeSheetDao timeSheetDao;

    @Resource(name = "scheduleDao")
    private ScheduleDao scheduleDao;

    @Resource(name = "shiftDao")
    private ShiftDao shiftDao;

    @Resource(name = "reservationDao")
    private ReservationDao reservationDao;

    public List<TimeSheetVo> getTimeSheet(String cTime, String eTime) {
        List<TimeSheetVo> voList = new ArrayList();
        List<Salon> list = salonDao.getSalon();
        for (Salon salon : list) {
            TimeSheetVo vo=new TimeSheetVo();
            vo.setSalonName(salon.getSalonName());
            List<TimeSheet> timeSheetList = new ArrayList();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("storeId", salon.getRecordId());
            String where = "store_id=#{storeId}";
            Map listMap = new HashMap();
            listMap.put("where", where);
            //门店下所有员工
            List<Stuff> list1 = stuffDao.getList(listMap, parameters);
            vo.setAttendance((long) list1.size());
            vo.setAttendanceNum((long) timeSheetList.size());
            vo.setAttendanceE((long) (list1.size()-timeSheetList.size()));
            for (Stuff stuff : list1) {
                //查询所有员工的出勤
                TimeSheet timeSheet=timeSheetDao.getTSheet(stuff.getRecordId(),cTime,eTime);
                if(timeSheet!=null){
                    timeSheetList.add(timeSheet);
                }
            }
            voList.add(vo);
        }
        return voList;
    }

    public List<Map> getTimeSheetBySalonId(Long salonId, String time) {
        List<Map> vo=new ArrayList<>();
        //门店下所有员工
        List<Stuff> list = stuffDao.getStuffForStoreId(salonId);
        for (Stuff stuff : list) {
            Map map = getTimeSheetByStuffId(stuff.getRecordId(), time);
            map.put("stuffId",stuff.getRecordId());
            map.put("stuffName",stuff.getStuffName());
            List<StuffVo> name = reservationDao.getStuffName(stuff.getRecordId());
            if(StringUtils.isEmpty(name)){
                map.put("role",name.get(0).getRole());
            }
            vo.add(map);
        }
        return vo;
    }

    public Map getTimeSheetByStuffId(Long stuffId, String time) {
        Map map=new HashMap();
        int i=0;
        //查询该员工一个月的排班,减掉休息就是应该出勤
        List<Schedule> list = scheduleDao.getPaiByStuffIdTime(stuffId, time);
        for (Schedule schedule : list) {
            if(schedule.getShiftId()!=-1){
                i++;
            }
        }
        List<TimeSheet> timeSheet=timeSheetDao.getTSheets(stuffId,time);
        //正常出勤,缺勤
        if(timeSheet!=null){
            map.put("attendance",timeSheet.size());
            if(i-timeSheet.size()>0){
                map.put("attendanceE",i-timeSheet.size());
            }else{
                map.put("attendanceE",0);
            }
        }
        //应出勤,
        map.put("attendanceNum",i);
        return map;
    }

    public List<Schedule> getTimeId(Long stuffId, String time) {
        List<Schedule> vo=new ArrayList<>();
        //查询该员工一个月的排班
        List<Schedule> list = scheduleDao.getPaiByStuffIdTime(stuffId, time);
        for (Schedule schedule : list) {
            if(schedule.getShiftId()!=-1){
                vo.add(schedule);
            }
        }
        return vo;
    }
    //查询员工未打卡记录详情
    public Map getTimeSheetDetails(Long stuffId, String time) {
        Map map=new HashMap();
        //查询该员工当天排班信息
        Schedule schedule=scheduleDao.getPaiByStuffId(stuffId,time);
        //查询该员工信息得到门店id
        Stuff stuff = stuffDao.getById(stuffId);
        Salon salon = salonDao.getSalonForId(stuff.getStoreId());
        //查询班次
        Shift shift=shiftDao.getById(schedule.getShiftId());
        //店名字,班次,上班时间,下班时间
        map.put("salonName",salon.getSalonName());
        map.put("shiftType",shift.getShiftType());//0.全  1.早   2. 中   3.晚
        map.put("timeStart",shift.getTimeStart());
        map.put("timeEnd",shift.getTimeEnd());
        return map;
    }

    public List<Map> getTimeSheets(Long recordId,String time) {
        List<Map> vo=new ArrayList<>();
        //查询该美容院下所有门店
        Map parameters = new HashMap();
        String where = "parent_id=#{parentId}";
        parameters.put("parentId", recordId);
        Map listMap = new HashMap();
        listMap.put("where", where);
        List<Salon> list = salonDao.getList(listMap, parameters);
        for (Salon salon : list) {
            Map reMap=new HashMap();
            reMap.put("recordId",salon.getRecordId());
            reMap.put("salonName",salon.getSalonName());
            int attendanceNum=0;
            int attendance=0;
            int attendanceE=0;
            //查询门店下所有员工
            List<Stuff> stuffList = stuffDao.getStuffForStoreId(salon.getRecordId());
            for (Stuff stuff : stuffList) {
                Map map = getTimeSheetByStuffId(stuff.getRecordId(), time);
                //应出勤,共出勤,异常
                attendanceNum+= Integer.parseInt(map.get("attendanceNum").toString());
                attendance+= Integer.parseInt(map.get("attendance").toString());
                attendanceE+= Integer.parseInt(map.get("attendanceE").toString());
            }
            reMap.put("attendanceNum",attendanceNum);
            reMap.put("attendance",attendance);
            reMap.put("attendanceE",attendanceE);
            vo.add(reMap);
        }
        return vo;
    }
}
