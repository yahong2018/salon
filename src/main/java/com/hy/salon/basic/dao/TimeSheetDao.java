package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.TimeSheet;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("timeSheetDao")
public class TimeSheetDao extends BaseDAOWithEntity<TimeSheet> {

    public TimeSheet getTSheet(Long recordId,String cTime, String eTime) {
        String where = "stuff_id=#{recordId} and time_start between #{cTime} and #{eTime}";
        //String where = "stuff_id=#{recordId}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", recordId);
        parameters.put("cTime", cTime);
        parameters.put("eTime", eTime);
        return this.getOne(where,parameters);
    }
    public TimeSheet getTSheets(Long recordId,String time) {
        String where = "stuff_id=#{recordId} and 'day'=#{time}";
        //String where = "stuff_id=#{recordId}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", recordId);
        parameters.put("time", time);
        return this.getOne(where,parameters);
    }
}
