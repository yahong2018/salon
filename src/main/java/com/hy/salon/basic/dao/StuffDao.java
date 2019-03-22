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


    public Stuff getStuffForUser(Long userId) {
        Map parameters = new HashMap();
        parameters.put("userId", userId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_STUFF_BY_USER, parameters);
    }


    public Map fuzzyQueryStuff(String  stuffName,Long salonId) {
        Map parameters = new HashMap();
        parameters.put("stuffName", stuffName);
        parameters.put("salonId", salonId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_FUZZY_QUERY_STUFF, parameters);
    }



    protected final static String SQL_GET_STUFF_BY_USER = "com.hy.salon.basic.dao.GET_STUFF_BY_USER";

    protected final static String SQL_FUZZY_QUERY_STUFF="com.hy.salon.basic.dao.FUZZY_QUERY_STUFF";




}
