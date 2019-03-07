package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ServiceSuite;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("ServiceSuiteDao")
public class ServiceSuiteDAO extends BaseDAOWithEntity<ServiceSuite> {


    public List<ServiceSuite> querySuitItemForCreateId(Long Id){
        String where="create_by = #{Id}";
        Map parameters = new HashMap();
        parameters.put("Id", Id);
        return this.getByWhere(where,parameters);


    }

    public ServiceSuite querySuitItemDataForId(Long Id){
        String where="record_id = #{Id}";
        Map parameters = new HashMap();
        parameters.put("Id", Id);
        return this.getOne(where,parameters);


    }

}
