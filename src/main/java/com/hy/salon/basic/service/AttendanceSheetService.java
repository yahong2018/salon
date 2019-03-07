package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.AttendanceSheetDAO;
import com.hy.salon.basic.dao.ScheduleDao;
import com.hy.salon.basic.dao.ShiftDao;
import com.hy.salon.basic.dao.TimeSheetDao;
import com.hy.salon.basic.entity.AttendanceSheet;
import com.hy.salon.basic.entity.Schedule;
import com.hy.salon.basic.entity.Shift;
import com.hy.salon.basic.entity.TimeSheet;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component("attendanceSheetService")
public class AttendanceSheetService {

    @Resource(name = "attendanceSheetDao")
    private AttendanceSheetDAO attendanceSheetDao;
    @Resource(name = "timeSheetDao")
    private TimeSheetDao timeSheetDao;
    @Resource(name = "scheduleDao")
    private ScheduleDao scheduleDao;
    @Resource(name = "shiftDao")
    private ShiftDao shiftDao;
    /**
     * 签到
     * @param condition
     * @return
     */
    public void insert(AttendanceSheet condition,Long storeId){
        //签到表插入一条数据
        attendanceSheetDao.insert(condition);
        //查询出勤表是否有记录
        String where = "stuff_id=#{stuffId} and time_start=#{timeStart}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", condition.getStuffId());
        parameters.put("timeStart", condition.getAttendanceTime());
        TimeSheet one = timeSheetDao.getOne(where, parameters);
        //查询该员工的排班信息
        String scheduleWhere = "stuff_id=#{stuffId}";
        Map<String, Object> scheduleParameter = new HashMap<>();
        scheduleParameter.put("stuffId", condition.getStuffId());
        Schedule schedule = scheduleDao.getOne(scheduleWhere, scheduleParameter);
        Shift shift=null;
        if(schedule.getShiftId()!=-1){
            //查询班次
            String shiftWhere = "store_id=#{storeId} and shift_type=#{shiftType}";
            Map<String, Object> shiftParameter = new HashMap<>();
            shiftParameter.put("storeId", storeId);
            shiftParameter.put("shiftType",schedule.getShiftId());
            shift=shiftDao.getOne(shiftWhere, shiftParameter);
        }
        if(one==null){
            TimeSheet timeSheet=new TimeSheet();
            timeSheet.setStuffId(condition.getStuffId());
            timeSheet.setDay(condition.getAttendanceTime());
            timeSheet.setTimeStart(condition.getAttendanceTime());
            if(condition.getAttendanceTime().compareTo(shift.getTimeStart())<=0){
                timeSheet.setTimeSheetType(0);//出勤类型正常
            }else{
                timeSheet.setTimeSheetType(1);//出勤类型迟到
            }
            timeSheetDao.insert(timeSheet);
        }else{
            if(condition.getAttendanceTime().compareTo(shift.getTimeEnd())<0){
                if(one.getTimeSheetType()==1){
                    one.setTimeSheetType(4);//4 又迟到又早退
                }else{
                    one.setTimeSheetType(2);//早退
                }
            }
            one.setTimeEnd(condition.getAttendanceTime());
            timeSheetDao.update(one);
        }
    }

}
