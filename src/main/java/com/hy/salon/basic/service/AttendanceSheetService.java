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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Transactional(rollbackFor = Exception.class)
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
    public void insert(AttendanceSheet condition,Long storeId) throws ParseException {
        //签到表插入一条数据
        attendanceSheetDao.insert(condition);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //查询出勤表是否有记录
        String where = "stuff_id=#{stuffId} and day=#{day}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", condition.getStuffId());
        parameters.put("day", sdf.format(condition.getAttendanceTime()));
        TimeSheet one = timeSheetDao.getOne(where, parameters);
        //查询该员工的排班信息
        String scheduleWhere = "stuff_id=#{stuffId} and day=#{day}";
        Map<String, Object> scheduleParameter = new HashMap<>();
        scheduleParameter.put("stuffId", condition.getStuffId());
        scheduleParameter.put("day", sdf.format(condition.getAttendanceTime()));
        Schedule schedule = scheduleDao.getOne(scheduleWhere, scheduleParameter);

        SimpleDateFormat sdfs = new SimpleDateFormat("HHmm");

        Shift shift=null;
        if(schedule.getShiftId()!=-1){
            //查询班次
            String shiftWhere = "store_id=#{storeId} and record_id=#{recordId}";
            Map<String, Object> shiftParameter = new HashMap<>();
            shiftParameter.put("storeId", storeId);
            shiftParameter.put("recordId",schedule.getShiftId());
            shift=shiftDao.getOne(shiftWhere, shiftParameter);
        }

        Calendar nowTime = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "HH:mm");
        String attendanceTime = dateFormat.format(condition.getAttendanceTime());
        System.out.println(attendanceTime);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd

        Date dt1 =null;//传进来的时间
        Date dt2 =null;//排班结束时间
        Date  dt4 = null;//排班开始时间
        try {
         dt1 = df.parse(attendanceTime);//将字符串转换为date类型
         dt2 = df.parse(shift.getTimeEnd());
            dt4 = df.parse(shift.getTimeStart());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long attendanceTimeL = dt1.getTime();
        long shiftTime = dt2.getTime();
        double temp = attendanceTimeL*1.0/shiftTime;
        long itemLong = (long) (temp * 1000);
        Date itemDate = new Date(itemLong);

        String itemDateTime = dateFormat.format(itemDate);
        Date dt3 = df.parse(itemDateTime);//中间时间
        if(one==null){
            TimeSheet timeSheet=new TimeSheet();
            timeSheet.setStuffId(condition.getStuffId());
            timeSheet.setDay(condition.getAttendanceTime());
            //传进来的时间大于下班时间
            if(dt1.getTime()>=dt2.getTime()){
                //说明是打的是下班卡
                timeSheet.setTimeEnd(condition.getAttendanceTime());
                timeSheet.setTimeSheetType(0);//出勤类型正常
            }else if((dt1.getTime()>dt3.getTime())&&(dt1.getTime()<dt2.getTime())){
                timeSheet.setTimeEnd(condition.getAttendanceTime());
                timeSheet.setTimeSheetType(2);//出勤类型早退
            }else{//早班
                if(dt1.getTime()<dt4.getTime()){
                    timeSheet.setTimeSheetType(0);//出勤类型正常
                    timeSheet.setTimeStart(condition.getAttendanceTime());
                }else{
                    timeSheet.setTimeSheetType(1);//出勤类型迟到
                    timeSheet.setTimeStart(condition.getAttendanceTime());
                }
            }
            timeSheetDao.insert(timeSheet);
        }else{
            if(dt1.getTime()<dt2.getTime()){
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
