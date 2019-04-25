package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.StuffScoreRecord;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("stuffScoreRecordDao")
public class StuffScoreRecordDAO extends BaseDAOWithEntity<StuffScoreRecord> {


    public List<Map<String,Object>> queryExisting( Long storeId) {
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_EXISTING_FOR_STORE, parameters);
    }

    public List<Map<String,Object>> queryExistingCount( Long storeId) {
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_EXISTING_COUNT_FOR_STORE, parameters);
    }

    public List<StuffScoreRecord> queryScoreRecord(Long storeId) {
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SCORE_RECORD_FOR_STORE, parameters);
    }

    protected final static String SQL_GET_SCORE_RECORD_FOR_STORE = "com.hy.salon.basic.dao.GET_SCORE_RECORD_FOR_STORE";


    protected final static String SQL_GET_EXISTING_COUNT_FOR_STORE = "com.hy.salon.basic.dao.GET_EXISTING_COUNT_FOR_STORE";


    protected final static String SQL_GET_EXISTING_FOR_STORE = "com.hy.salon.basic.dao.GET_EXISTING_FOR_STORE";
}
