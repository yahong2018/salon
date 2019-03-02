package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.StoreRoomDao;
import com.hy.salon.basic.entity.StoreRoom;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("storeRoomService")
public class StoreRoomService {

    @Resource(name = "storeRoomDao")
    private StoreRoomDao storeRoomDao;


    public List<StoreRoom> getRoomForStoreId(String storeId){


        return storeRoomDao.getRoomForStoreId(storeId);
    }

    public int doInsert(StoreRoom item) {

        return storeRoomDao.insert(item);

    }
    public StoreRoom getRoomForName(String name,Long storeId){
        return storeRoomDao.getRoomForName(name,storeId);
    }

    public int updateRoom(StoreRoom item){
        return storeRoomDao.update(item);
    }



}
