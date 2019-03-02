package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.dao.StoreRoomDao;
import com.hy.salon.basic.entity.StoreRoom;
import com.hy.salon.basic.service.StoreRoomService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/storeRoom")
public class StoreRoomController extends SimpleCRUDController<StoreRoom> {

    @Resource(name = "storeRoomDao")
    private StoreRoomDao storeRoomDao;


    @Override
    protected BaseDAOWithEntity<StoreRoom> getCrudDao() {
        return storeRoomDao;
    }

    @Resource(name = "storeRoomService")
    private StoreRoomService storeRoomService;


    /**
     * 获取门店下房间信息
     */
    @ResponseBody
    @RequestMapping("getSroreRoom")
    public Map getSroreRoom(HttpServletResponse resp,String recordId){
        Map map =new HashMap<String, Object>();

        if( null == recordId || "".equals(recordId)){
            map.put("respCode","0001");
            map.put("respDesc","美容院号不能为空");

            return map;
        }
        List<StoreRoom> roomList=storeRoomService.getRoomForStoreId(recordId);

        map.put("respCode","0000");
        map.put("roomList",roomList);

        return map;

    }


    /**
     * 添加房间
     */
    @ResponseBody
    @RequestMapping("addStoreRoom")
    public Map addStoreRoom(HttpServletResponse resp, StoreRoom storeRoom){

        JSONObject jsonObj=new JSONObject();
        Map map =new HashMap<String, Object>();



        if(null == storeRoom.getStoreId() || null == storeRoom.getRoomName() || "".equals(storeRoom.getRoomName()) || null == storeRoom.getRecordStatus()

        ){

            map.put("respCode","0001");
            map.put("respDesc","非空字段不能为空");


            return map;
        }
        //判断该门店该房间是否重名
        StoreRoom condition=storeRoomService.getRoomForName(storeRoom.getRoomName(),storeRoom.getStoreId());
        if(null != condition){

            map.put("respCode","0001");
            map.put("respDesc","该房间名已被使用");
            return map;
        }

        storeRoomService.doInsert(storeRoom);

        map.put("respCode","0000");

        return map;

    }
    /**
     * 修改房间信息
     */
    @ResponseBody
    @RequestMapping("updateRoomData")
    public Map updateRoomData(HttpServletResponse resp,StoreRoom storeRoom){
        Map map =new HashMap<String, Object>();
        storeRoomService.updateRoom(storeRoom);

        map.put("respCode","0000");

        return map;


    }


}
