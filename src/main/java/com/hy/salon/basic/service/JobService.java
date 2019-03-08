package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.JobDAO;
import com.hy.salon.basic.dao.ProductDao;
import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.StuffJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("jobService")
public class JobService {

    @Resource(name = "jobDAO")
    private JobDAO jobDAO;

   /* public List<StuffJob> getStuffJobList(long stuffId) {
        String where="stuff_id=#{stuffId}";
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        return stuffJobDao.getByWhere(where,parameters);
    }*/
    public List<Job> getJobList(long stuffId) {
        String where="stuff_id=#{stuffId}";
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        return jobDAO.getSqlHelper().getSqlSession().selectList(GET_JOB_BY_USERID,parameters);
       // return jobDAO.getByWhere(where,parameters);
    }
    protected final static String GET_JOB_BY_USERID = "com.hy.salon.basic.dao.GET_JOB_BY_USERID";
}
