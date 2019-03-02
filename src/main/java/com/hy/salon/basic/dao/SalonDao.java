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

    public Salon getSalonForId(String id){
        String where = "record_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }

    public List<Salon> getSalonForStoreId(String parentId){
        Map parameters = new HashMap();
        parameters.put("parentId", parentId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SALON_FOR_PARENTId, parameters);
    }





    protected final static String SQL_GET_SALON_FOR_PARENTId = "com.hy.salon.basic.dao.GET_SALON_FOR_PARENTId";


}
