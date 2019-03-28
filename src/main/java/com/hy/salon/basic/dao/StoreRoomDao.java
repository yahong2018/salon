package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.StoreRoom;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("storeRoomDao")
public class StoreRoomDao extends BaseDAOWithEntity<StoreRoom> {




    public List<StoreRoom> getRoomForStoreId(String storeId){
        String where="store_id=#{storeId}";
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getByWhere(where,parameters);
    }

    public StoreRoom getRoomForRecordId(Long recordId){
        String where="record_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getOne(where,parameters);
    }

    public StoreRoom getRoomForName(String name,Long storeId){
        String where = "room_name=#{roomName} and store_id=#{storeId}";
        Map parameters = new HashMap();
        parameters.put("roomName", name);
        parameters.put("storeId", storeId);

        return this.getOne(where, parameters);
    }








}
