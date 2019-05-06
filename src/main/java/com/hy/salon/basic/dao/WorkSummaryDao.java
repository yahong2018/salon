package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.StuffScoreRecord;
import com.hy.salon.basic.entity.WorkSummary;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("workSummaryDao")
public class WorkSummaryDao extends BaseDAOWithEntity<WorkSummary> {

    public Map<String,Object> querySummarySize(Long storeId) {
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_SUMMARY_SIZE_FRO_STORE, parameters);
    }

    protected final static String SQL_GET_SUMMARY_SIZE_FRO_STORE = "com.hy.salon.basic.dao.GET_SUMMARY_SIZE_FRO_STORE";




    public List<WorkSummary> getSummaryForStuff(Long stuffId,String startTime,String endTime){
        String where = "stuff_dd=#{stuffId} ";

        if(null!=startTime){
            where = "and create_date > #{startTime} and create_date < #{endTime}";
        }

        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);

        return this.getByWhere(where, parameters);
    }

}
