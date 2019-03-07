package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.StuffJobDao;
import com.hy.salon.basic.entity.StuffJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("stuffJobService")
public class StuffJobService {
    @Resource(name = "stuffJobDao")
    private StuffJobDao stuffJobDao;
    public List<StuffJob> getStuffJobList(long stuffId) {
        String where="stuff_id=#{stuffId}";
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
       return stuffJobDao.getByWhere(where,parameters);
    }
}
