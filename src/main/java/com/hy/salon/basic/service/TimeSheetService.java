package com.hy.salon.basic.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.util.DateString;
import com.hy.salon.basic.vo.MyResult;
import com.hy.salon.basic.vo.StuffVo;
import com.hy.salon.basic.vo.TimeSheetVo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthTableUI;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;
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

    public List<Map> getTimeSheetBySalonId(MyResult result, Long salonId, String time) {
        List<Map> vo=new ArrayList<>();
        //门店下所有员工
        List<Stuff> list = stuffDao.getStuffForStoreId(salonId);

        for (Stuff stuff : list) {
            /*String aNum = "";
            Integer haNum= 0;
            Integer yNum = 0;*/
            MyResult result1 = new MyResult();
            Map map = getTimeSheetByStuffIdNew(result1,stuff.getRecordId(), time);
            map.put("stuffId",stuff.getRecordId());
            map.put("stuffName",stuff.getStuffName());
            map.put("tel",stuff.getTel());
            map.put("picturesUrl","");
            Pictures  pictures = picturesDao.getOnePicturesForCondition(stuff.getRecordId(), (byte) 1, (byte) 0);
            if(pictures!=null){
                map.put("picturesUrl",pictures.getPicUrl());
            }
            List<StuffVo> name = reservationDao.getStuffName(stuff.getRecordId());
            if(name.size()>0){
                map.put("roleName",name.get(0).getRole());
            }else{
                map.put("roleName","无");
            }
            result.setAttendanceNum(result.getAttendanceNum()+result1.getAttendanceNum());
            result.setHasAttendanceNum(result.getHasAttendanceNum()+result1.getHasAttendanceNum());
            result.setYichangNum(result.getYichangNum()+result1.getYichangNum());
            vo.add(map);
        }
        return vo;
    }

    public List<Map> getTimeSheetBySalonId(Long salonId, String time) {
        List<Map> vo=new ArrayList<>();
        //门店下所有员工
        List<Stuff> list = stuffDao.getStuffForStoreId(salonId);

        for (Stuff stuff : list) {
          /* MyResult result =new  MyResult();
            Map map = getTimeSheetByStuffId(result,stuff.getRecordId(), time);
            map.put("stuffId",stuff.getRecordId());
            map.put("stuffName",stuff.getStuffName());
            map.put("tel",stuff.getTel());
            List<StuffVo> name = reservationDao.getStuffName(stuff.getRecordId());
            if(StringUtils.isEmpty(name)){
                map.put("role",name.get(0).getRole());
            }
//            attendanceNum = attendanceNum+aNum;
//            hasAttendanceNum = hasAttendanceNum+haNum;
//            yichangNum = yichangNum+yNum;
            vo.add(map);*/
        }
        return vo;
    }

    /**
     * 员工姓名条件搜索
     * @param salonId
     * @param time
     * @param name
     * @return
     */
    public List<Map> getTimeSheetBySalonId(int attendanceNum,int hasAttendanceNum,int yichangNum,Long salonId, String time,String name) {
        List<Map> vo=new ArrayList<>();
        //门店下所有员工
        List<Stuff> list = stuffDao.getStuffForStoreId(salonId);
        MyResult result = new MyResult();
        for (Stuff stuff : list) {
            if(stuff.getStuffName().equals(name)){
               /* Map map = getTimeSheetByStuffId(result,stuff.getRecordId(), time);
                map.put("stuffId",stuff.getRecordId());
                map.put("stuffName",stuff.getStuffName());
                map.put("tel",stuff.getTel());
                List<StuffVo> StuffList = reservationDao.getStuffName(stuff.getRecordId());
                if(StringUtils.isEmpty(StuffList)){
                    map.put("role",StuffList.get(0).getRole());
                }
                vo.add(map);*/
            }
        }
        return vo;
    }
    /**
     * 一个月的异常
     * @param result
     * @param stuffId
     * @param time
     * @return
     */
    public JSONArray getYCTimeSheetByStuffIdNew(MyResult result,Long stuffId, String time,String type) {
        JSONArray jsonArray = new JSONArray();

        List<TimeSheet> timeSheet=timeSheetDao.getTSheetsByType(stuffId,time,type);

        //查询该员工一个月的排班,减掉休息就是应该出勤
        if("queqin".equals(type)){
            List<Schedule> list = scheduleDao.getPaiByStuffIdTime(stuffId, time);
            result.setAttendanceNum(list.size());
            for (Schedule schedule : list) {
                Boolean flag = false;
                for (TimeSheet ts :timeSheet){
                        if(!schedule.getDay().equals(ts.getDay())){
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("timeStart", DateString.DateToString2(ts.getTimeStart()));
                            jsonObject.put("timeEnd",DateString.DateToString2(ts.getTimeEnd()));
                            jsonObject.put("day",DateString.DateToString(schedule.getDay()));
                            jsonObject.put("type","缺勤");
                            jsonArray.add(jsonObject);
                        }

                }

            }
        }else{
            for (TimeSheet ts :timeSheet){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("timeStart",DateString.DateToString2(ts.getTimeStart()));
                jsonObject.put("timeEnd",DateString.DateToString2(ts.getTimeEnd()));
                jsonObject.put("day",DateString.DateToString(ts.getDay()));

                if("chidao".equals(type)){
                    jsonObject.put("type","迟到");
                }
                if("zaotui".equals(type)){
                    jsonObject.put("type","早退");
                }
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray;
    }
    /**
     * 一个月的
     * @param result
     * @param stuffId
     * @param time
     * @return
     */
    public Map getTimeSheetByStuffIdNew(MyResult result,Long stuffId, String time) {
        Map map=new HashMap();
        int i=0;
        //查询该员工一个月的排班,减掉休息就是应该出勤
        List<Schedule> list = scheduleDao.getPaiByStuffIdTime(stuffId, time);
        result.setAttendanceNum(list.size());
        map.put("attendanceNum",list.size());
        List<TimeSheet> timeSheet=timeSheetDao.getTSheets(stuffId,time);

        result.setHasAttendanceNum(timeSheet.size());
        map.put("hasAttendanceNum",timeSheet.size());
        int yInt = 0;

        int lateNum = 0;
        int leaveEarlyNum = 0;
        int absenceFromDutyNum = 0;
        int checkinOmission = 0;
        int signOmission = 0;
        int leaveNum = 0;
        int temp = 0;
        for (Schedule schedule : list) {
            Boolean flag = false;
            for (TimeSheet ts :timeSheet){
                if(temp==0){
                    if(ts.getTimeSheetType()==4){
                        lateNum = lateNum+1;
                        leaveEarlyNum = leaveEarlyNum+1;
                    }else{
                        if(ts.getTimeSheetType()==1){
                            lateNum = lateNum+1;
                        }else if(ts.getTimeSheetType()==2){
                            leaveEarlyNum = leaveEarlyNum+1;
                        }else if(ts.getTimeSheetType()==3){
                            leaveNum = leaveNum+1;
                        }
                    }


/*                    if(ts.getTimeStart()==null||ts.getTimeStart().equals("")){
                        checkinOmission = checkinOmission+1;
                    }else if(ts.getTimeEnd()==null||ts.getTimeEnd().equals("")){
                        signOmission = signOmission+1;
                    }*/

                    if(ts.getTimeSheetType()!=0){
                        yInt=yInt+1;
                    }
                }
            }
            temp ++;
        }
        absenceFromDutyNum = list.size() - timeSheet.size();
        result.setYichangNum(yInt);
        map.put("yichangNum",yInt);
        map.put("lateNum",lateNum);//迟到
        map.put("leaveEarlyNum",leaveEarlyNum);//早退
        map.put("absenceFromDutyNum",absenceFromDutyNum);//缺勤
        map.put("checkinOmission",checkinOmission);//签到遗漏
        map.put("signOmission",signOmission);//签退遗漏
        map.put("leaveNum",leaveNum);//请假天数
        //正常出勤,缺勤
        /*if(timeSheet!=null){
            map.put("attendance",timeSheet.size());
            if(i-timeSheet.size()>0){
                map.put("attendanceE",i-timeSheet.size());
            }else{
                map.put("attendanceE",0);
            }
        }*/
        //应出勤,
        //map.put("attendanceNum",i);
        return map;
    }

    /**
     * 一天的
     * @param result
     * @param stuffId
     * @param time
     * @return
     */
    public Map getTimeSheetOneDayByStuffId(MyResult result,Long stuffId, String time) {
        Map map=new HashMap();
        int i=0;
        //查询该员工一天的排班,减掉休息就是应该出勤
        Schedule schedule = scheduleDao.getPaiByStuffId(stuffId, time);

        if(schedule!=null){
            result.setAttendanceNum(result.getAttendanceNum()+1);
            String startTime = time;
            /*Date endTime = DateString.StringToDate(time);
            Calendar c = Calendar.getInstance();
            c.setTime(endTime);
            c.add(Calendar.DAY_OF_MONTH, 1);
            Date temp = c.getTime();*/


            Date date = DateString.StringToDateAddNum2(time,1);
            TimeSheet timeSheet=timeSheetDao.getTSheet(stuffId,time+" 00:00:00",DateString.DateToString(date)+" 00:00:00");
            if(timeSheet!=null){
                result.setHasAttendanceNum(result.getHasAttendanceNum()+1);
            }
            if(timeSheet!=null){
                if(timeSheet.getTimeSheetType()!=0){
                    result.setYichangNum(result.getYichangNum()+1);
                }
            }


        }


        //result.setYichangNum();
/*
        int yInt = 0;
        int lateNum = 0;
        int leaveEarlyNum = 0;
        int absenceFromDutyNum = 0;
        int checkinOmission = 0;
        int signOmission = 0;
        int leaveNum = 0;
        int temp = 0;*/
/*        for (Schedule schedule : list) {
            Boolean flag = false;
            for (TimeSheet ts :timeSheet){
                if(temp==0){
                    if(ts.getTimeSheetType()==1){
                        lateNum = lateNum+1;
                    }else if(ts.getTimeSheetType()==2){
                        leaveEarlyNum = leaveEarlyNum+1;
                    }else if(ts.getTimeSheetType()==3){
                        leaveNum = leaveNum+1;
                    }

                    if(ts.getTimeStart()==null||ts.getTimeStart().equals("")){
                        checkinOmission = checkinOmission+1;
                    }else if(ts.getTimeEnd()==null||ts.getTimeEnd().equals("")){
                        signOmission = signOmission+1;
                    }

                    if(ts.getTimeSheetType()!=0){
                        yInt=yInt+1;
                    }
                }
                if(schedule.getDay().equals(ts.getDay())){
                    flag = true;
                    break;
                }else{
                    flag = false;
                }

            }
            if(!flag){
                absenceFromDutyNum = absenceFromDutyNum+1;
            }
            temp ++;
        }*/
/*        result.setYichangNum(yInt);
        map.put("lateNum",lateNum);
        map.put("leaveEarlyNum",leaveEarlyNum);
        map.put("absenceFromDutyNum",absenceFromDutyNum);
        map.put("checkinOmission",checkinOmission);
        map.put("signOmission",signOmission);
        map.put("leaveNum",leaveNum);*/

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
       try{
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           String curDate = sdf.format(sdf.parse(time));

           //查询该员工当天排班信息
           Schedule schedule=scheduleDao.getPaiByStuffId(stuffId,curDate);

        //查询该员工信息得到门店id
        Stuff stuff = stuffDao.getById(stuffId);
        Salon salon = salonDao.getSalonForId(stuff.getStoreId());
        //查询班次
        Shift shift=shiftDao.getById(schedule.getShiftId());
        //店名字,班次,上班时间,下班时间
        map.put("salonName",salon.getSalonName());
        if(shift!=null){
            map.put("shiftType",shift.getShiftType());//0.全  1.早   2. 中   3.晚
            map.put("timeStart",shift.getTimeStart());
            map.put("timeEnd",shift.getTimeEnd());
        }

       }catch (Exception e){
           e.printStackTrace();
       }
        return map;
    }

    /**
     * 获取各个门店的一天的考勤
     * @param recordId
     * @param time
     * @param page
     * @param limit
     * @return
     */
    public List<Map> getTimeSheets(Long recordId,String time,Integer page,Integer limit) {
        List<Map> vo=new ArrayList<>();
        //查询该美容院下所有门店
        Map parameters = new HashMap();
        String where = "parent_id=#{parentId} or record_id=#{recordId}";
        parameters.put("parentId", recordId);
        parameters.put("recordId", recordId);
        Map listMap = new HashMap();
        listMap.put("where", where);
        PageHelper.startPage(page, limit);
        List<Salon> list = salonDao.getList(listMap, parameters);
        for (Salon salon : list) {
            Map reMap=new HashMap();
            reMap.put("recordId",salon.getRecordId());
            reMap.put("salonName",salon.getSalonName());
            //查询门店下所有员工
            List<Stuff> stuffList = stuffDao.getStuffForStoreId(salon.getRecordId());
            MyResult result = new MyResult();
            for (Stuff stuff : stuffList) {
                Map map = getTimeSheetOneDayByStuffId(result,stuff.getRecordId(), time);
            }
            reMap.put("attendanceNum",result.getAttendanceNum());
            reMap.put("hasAttendanceNum",result.getHasAttendanceNum());
            reMap.put("yichangNum",result.getYichangNum());
            vo.add(reMap);
        }
        return vo;
    }

    public List<Salon> getSalon(Long storeId) {
        Map parameters = new HashMap();
        String where = "parent_id=#{parentId} or record_id=#{record_id}";
        parameters.put("parentId", storeId);
        parameters.put("record_id",storeId);
        Map listMap = new HashMap();
        listMap.put("where", where);
        List<Salon> list = salonDao.getList(listMap, parameters);
        return list;
    }

    public List<Map> getTimeSheetInit(int attendanceNum,int hasAttendanceNum,int yichangNum,Long salonId, String time) {
        PageHelper.startPage(Integer.parseInt("0"), Integer.parseInt("10"));
        List<Map> map=new ArrayList<>();
        List<Salon> salon = getSalon(salonId);
        for (Salon salon1 : salon) {
//            List<Map> list = getTimeSheetBySalonId( "", "", "",salon1.getRecordId(), time);
//            map.addAll(list);
        }
        return map;
    }

   /* public List<Map> getYCTimeSheet(Long stuffId, String time, String type) {

        //timeSheetDao.getTSheets();
    }*/
}
