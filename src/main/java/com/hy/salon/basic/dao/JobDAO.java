package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.Salon;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("jobDAO")
public class JobDAO extends BaseDAOWithEntity<Job> {

    public Job getJobForId(Long jobId){
        String where = "record_id=#{jobId}";
        Map parameters = new HashMap();
        parameters.put("jobId", jobId);

        return this.getOne(where, parameters);
    }
}
