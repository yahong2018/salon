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

    public Schedule getPaiByStuffId(Long stuffId, String time) {
        String where = "stuff_id=#{stuffId} and day=#{time}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", stuffId);
        parameters.put("time", time);
        return this.getOne(where,parameters);
    }
    //查询当月一个月的排班
    public List<Schedule> getPaiByStuffIdTime(Long stuffId, String time) {
        String timeStart=time+"00";
        String timeEnd=time+"31";
        String where = "stuff_id=#{stuffId} and day between  #{timeStart} and  #{timeEnd}";
        Map map=new HashMap();
        map.put("where",where);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", stuffId);
        parameters.put("timeStart", timeStart);
        parameters.put("timeEnd", timeEnd);
        return this.getList(map,parameters);
    }
}
