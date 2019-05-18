package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ConsumeRecord;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("consumeRecordDao")
public class ConsumeRecordDAO extends BaseDAOWithEntity<ConsumeRecord> {




    protected final static String SQL_QUERY_CONSUME_RECORD = "com.hy.salon.basic.dao.QUERY_CONSUME_RECORD";

    public List<Map<String,Object>> getConsumeRecord(Long storeId, String role, String toDays, String memberName) {
        Map parameters = new HashMap();

        if (StringUtils.isNotEmpty(toDays)) {
            String days[] = toDays.split("~");
            String timeStart = days[0];
            String timeEnd = days[1];
            parameters.put("timeStart", timeStart);
            parameters.put("timeEnd", timeEnd);
        }
        parameters.put("storeId", storeId);
        parameters.put("role", role);
        parameters.put("memberName", memberName);
        return this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_CONSUME_RECORD, parameters);
    }




}
