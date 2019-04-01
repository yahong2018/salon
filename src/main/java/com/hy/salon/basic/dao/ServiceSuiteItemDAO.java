package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ServiceSeries;
import com.hy.salon.basic.entity.ServiceSuiteItem;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("serviceSuiteItemDao")
public class ServiceSuiteItemDAO extends BaseDAOWithEntity<ServiceSuiteItem> {



    public List<ServiceSuiteItem> querySuitItemForId(Long Id){
        String where="service_suite_id = #{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", Id);
        return this.getByWhere(where,parameters);


    }

    public ServiceSuiteItem querySuitItemForId(Long suiteId,String serviceId){
        String where="service_suite_id = #{suiteId} and service_id = #{serviceId}";
        Map parameters = new HashMap();
        parameters.put("suiteId", suiteId);
        parameters.put("serviceId", serviceId);
        return this.getOne(where,parameters);


    }






}
