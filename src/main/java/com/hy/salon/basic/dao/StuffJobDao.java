package com.hy.salon.basic.dao;


import com.hy.salon.basic.entity.StuffJob;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("stuffJobDao")
public class StuffJobDao extends BaseDAOWithEntity<StuffJob> {


    public StuffJob getStuffJobForStuff(Long stuffId){
        String where = "stuff_id=#{stuffId}";
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        return this.getOne(where, parameters);
    }

    public List<StuffJob> getStuffJobListForStuff(Long stuffId){
        String where = "stuff_id=#{stuffId}";
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        return this.getByWhere(where, parameters);
    }
}
