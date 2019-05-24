package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.*;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("jobDAO")
public class JobDAO extends BaseDAOWithEntity<Job> {

    public Job getJobForId(Long jobId){
        String where = "record_id=#{jobId}";
        Map parameters = new HashMap();
        parameters.put("jobId", jobId);

        return this.getOne(where, parameters);
    }

    public Job getJobForJobLevel(Byte jobLevel){
        String where = "job_level=#{jobLevel}";
        Map parameters = new HashMap();
        parameters.put("jobLevel", jobLevel);

        return this.getOne(where, parameters);
    }

    public List<Job> queryJobId(Long recordId){
        Map parameters = new HashMap();
        parameters.put("record_id", recordId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_NAMEAa, parameters);
    }

    public List<Job> getJobForCondition(Long recordId){

        String where = "record_id=#{recordId} ";


        Map parameters = new HashMap();
        parameters.put("record_id", recordId);

        return this.getByWhere(where, parameters);
    }

    public List<Job> getSeriesForUser(){
        String where = "";
        Map parameters = new HashMap();


        return this.getByWhere(where, parameters);
    }


    public List<Map<String,Object>> getConsumeRecord(Long recordId) {
        Map parameters = new HashMap();

        parameters.put("recordId", recordId);

        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_NAMEAa, parameters);
    }
    protected final static String SQL_GET_STUFF_NAMEAa = "com.hy.salon.basic.dao.GET_STUFF_NAMEAa";
}
