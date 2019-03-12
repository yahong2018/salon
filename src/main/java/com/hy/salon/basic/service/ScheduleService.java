package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ScheduleDao;
import com.hy.salon.basic.entity.Schedule;
import com.hy.salon.basic.vo.ShiftVo;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("scheduleService")
public class ScheduleService {
    @Resource(name = "scheduleDao")
    private ScheduleDao scheduleDao;

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
            scheduleDao.insert(schedule);
        }
    }
}
