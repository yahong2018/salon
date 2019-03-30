package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.AttendanceSheet;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("attendanceSheetDao")
public class AttendanceSheetDAO extends BaseDAOWithEntity<AttendanceSheet> {

    public List<AttendanceSheet> getTAttendanceSheet(Long recordId, String cTime, String eTime) {
        String where = "stuff_id=#{stuffId} and attendance_time between #{cTime} and #{eTime}";
        //String where = "stuff_id=#{recordId}";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("stuffId", recordId);
        parameters.put("cTime", cTime);
        parameters.put("eTime", eTime);
        return this.getByWhere(where,parameters);
    }
}
