package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Schedule;
import com.hy.salon.basic.vo.ShiftVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("scheduleDao")
public class ScheduleDao extends BaseDAOWithEntity<Schedule> {
    public List<ShiftVo> getScheduleByTime(Long recordId, String timeStart, String timeEnd) {
        Map parameters = new HashMap();
        parameters.put("storeId", recordId);
        parameters.put("timeStart", timeStart);
        parameters.put("timeEnd", timeEnd);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SCHEDULE_BY_TIME, parameters);
    }
    protected final static String SQL_GET_SCHEDULE_BY_TIME = "com.hy.salon.basic.dao.GET_SCHEDULE_BY_TIME";
}
