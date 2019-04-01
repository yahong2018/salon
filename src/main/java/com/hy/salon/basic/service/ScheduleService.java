package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.ScheduleDao;
import com.hy.salon.basic.entity.Schedule;
import com.hy.salon.basic.vo.ShiftVo;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("scheduleService")
public class ScheduleService {
    @Resource(name = "scheduleDao")
    private ScheduleDao scheduleDao;
    public ScheduleDao getScheduleDao() {
        return this.scheduleDao;
    }
    public List<ShiftVo> getScheduleByTime(Long recordId, String timeStart, String timeEnd) {
        return scheduleDao.getScheduleByTime(recordId,timeStart,timeEnd);
    }

    public void updateStuffSchedule(List<Schedule> list) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Schedule schedule : list) {
            //查询该员工的排班信息
            String scheduleWhere = "stuff_id=#{stuffId} and day=#{day}";
            Map<String, Object> scheduleParameter = new HashMap<>();
            scheduleParameter.put("stuffId", schedule.getStuffId());
            scheduleParameter.put("day", sdf.format(schedule.getDay()));
            Schedule one = scheduleDao.getOne(scheduleWhere, scheduleParameter);
            if(one!=null){
                scheduleDao.deleteById(one.getRecordId());
            }
            if(schedule.getShiftId()==-1){
                scheduleDao.deleteById(one.getRecordId());
            }
            scheduleDao.insert(schedule);
        }
    }

    public ExtJsResult getScheduleForStoreIdSystem(Date[] dataList,Date timeStartDate, Date timeEndDate,HttpServletRequest request, Long recordId, ListRequestBaseHandler listRequestBaseHandler) {
        return scheduleDao.getScheduleForStoreIdSystem(dataList,timeStartDate,timeEndDate,request,recordId,listRequestBaseHandler);

    }

    public ExtJsResult getAdminStuffScheduleByTime(Date[] dataList, Date timeStartDate, Date timeEndDate, Long recordId, String stuffId) {
        return scheduleDao.getAdminStuffScheduleByTime(dataList,timeStartDate,timeEndDate,recordId,stuffId);
    }
}
