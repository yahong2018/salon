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


    public List<Map<String,String>>  fuzzyQueryStuff(String  stuffName,Long salonId,String jobLevel) {
        Map parameters = new HashMap();
        stuffName="%"+stuffName+"%";
        parameters.put("stuffName", stuffName);
        parameters.put("salonId", salonId);
        if("0".equals(jobLevel)){
            return this.getSqlHelper().getSqlSession().selectList(SQL_FUZZY_QUERY_STUFF, parameters);
        }else{
            return this.getSqlHelper().getSqlSession().selectList(SQL_FUZZY_QUERY_STUFF2, parameters);
        }

    }



    protected final static String SQL_GET_STUFF_BY_USER = "com.hy.salon.basic.dao.GET_STUFF_BY_USER";

    protected final static String SQL_FUZZY_QUERY_STUFF="com.hy.salon.basic.dao.FUZZY_QUERY_STUFF";

    protected final static String SQL_FUZZY_QUERY_STUFF2="com.hy.salon.basic.dao.FUZZY_QUERY_STUFF2";




}
