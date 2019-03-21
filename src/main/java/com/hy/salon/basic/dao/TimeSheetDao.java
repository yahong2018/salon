package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.TimeSheet;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("timeSheetDao")
public class TimeSheetDao extends BaseDAOWithEntity<TimeSheet> {

    public TimeSheet getTSheet(Long recordId,String cTime, String eTime) {
        String where = "stuff_id=#{stuffId} and time_start between #{cTime} and #{eTime}";
        //String where = "stuff_id=#{recordId}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", recordId);
        parameters.put("cTime", cTime);
        parameters.put("eTime", eTime);
        return this.getOne(where,parameters);
    }
    //查询当月员工出勤记录
    public List<TimeSheet> getTSheets(Long recordId, String time) {
        String timeStart=time+"00";
        String timeEnd=time+"31";
        String where = "stuff_id=#{stuffId} and day between  #{timeStart} and  #{timeEnd}";
        Map map=new HashMap();
        map.put("where",where);
        //String where = "stuff_id=#{recordId}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", recordId);
        parameters.put("timeStart", timeStart);
        parameters.put("timeEnd", timeEnd);
        return this.getList(map,parameters);
    }
}
