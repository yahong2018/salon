package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.VipSuite;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("vipSuiteDao")
public class VipSuiteDAO extends BaseDAOWithEntity<VipSuite> {

    public VipSuite getVipSuiteForId(Long id){
        String where = "record_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }



}
