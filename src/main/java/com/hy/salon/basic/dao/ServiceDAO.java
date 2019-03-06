package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Service;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("serviceDao")
public class ServiceDAO extends BaseDAOWithEntity<Service> {

    public int insertService(Service condition){
        return insert(condition);
    }

    public List<Service> queryServiceForId(Long Id){
        String where="create_by=#{Id}";
        Map parameters = new HashMap();
        parameters.put("Id", Id);
        return this.getByWhere(where,parameters);
    }

    public Service queryOneService(Long recordId){
        String where="record_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getOne(where,parameters);

    }




}
