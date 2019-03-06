package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Stuff;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("stuffDao")
public class StuffDao extends BaseDAOWithEntity<Stuff>{

//    public List<Stuff> getStuffForStoreId(Long storeId){
//        Map parameters = new HashMap();
//        parameters.put("storeId", storeId);
//
//        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_STUFF_FOR_STOREID, parameters);
//    }

    public List<Stuff> getStuffForStoreId(Long storeId){
        String where="store_id=#{storeId}";
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);

        return this.getByWhere(where,parameters);
    }




}
