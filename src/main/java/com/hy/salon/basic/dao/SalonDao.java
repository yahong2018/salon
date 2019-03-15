package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Salon;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("salonDao")
public class SalonDao extends BaseDAOWithEntity<Salon> {

    public List<Salon> getSalon(){
        return this.getAll();
    }

    public Salon getSalonForId(Long id){
        String where = "record_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }

    public Salon getSalonForStoreId(Long id){
        String where = "record_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }




    public List<Salon> getSalonForStoreId2(Long parentId){
        String where="parent_id=#{parentId}";
        Map parameters = new HashMap();
        parameters.put("parentId", parentId);
        return this.getByWhere(where,parameters);
    }


    public List<Salon> getSalonForCreateId(Long createId){
        String where="create_by=#{createId} and parent_id != -1";
        Map parameters = new HashMap();
        parameters.put("createId", createId);
        return this.getByWhere(where,parameters);
    }








}
