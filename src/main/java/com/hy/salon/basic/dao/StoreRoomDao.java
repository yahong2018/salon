package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.StoreRoom;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("storeRoomDao")
public class StoreRoomDao extends BaseDAOWithEntity<StoreRoom> {


    public List<StoreRoom> getRoomForStoreId(String storeId){
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_ROOM_FOR_STOREId, parameters);
    }

//    @Override
//    protected int doInsert(StoreRoom item) {
//        return this.getSqlHelper().getSqlSession().insert(SQL_INSERT_STOREROOM, item);
//    }

    public StoreRoom getRoomForName(String name,Long storeId){
        String where = "room_name=#{roomName} and store_id=#{storeId}";
        Map parameters = new HashMap();
        parameters.put("roomName", name);
        parameters.put("storeId", storeId);

        return this.getOne(where, parameters);
    }




    protected final static String SQL_GET_ROOM_FOR_STOREId = "com.hy.salon.basic.dao.GET_ROOM_FOR_STOREId";
//    protected final static String SQL_INSERT_STOREROOM = "com.hy.salon.basic.dao.INSERT_STOREROOM";




}
