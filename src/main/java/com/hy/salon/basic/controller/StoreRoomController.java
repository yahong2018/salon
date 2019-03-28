package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.StoreRoomDao;
import com.hy.salon.basic.entity.StoreRoom;
import com.hy.salon.basic.service.StoreRoomService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/storeRoom")
@Api(value = "StoreRoomController| 房间控制器")
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
    @RequestMapping("getStoreRoom")
    @ApiOperation(value="获取门店下房间信息", notes="以门店Id获取门店下房间信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店Id", required = true, dataType = "Long"),

    })
    public Result getStoreRoom(String recordId,int page){
        Result r= new Result();



        r.setTotal(storeRoomService.getRoomForStoreId(recordId).size());
        PageHelper.startPage(page, 10);
        List<StoreRoom> roomList=storeRoomService.getRoomForStoreId(recordId);

        r.setMsgcode(StatusUtil.OK);
        r.setMsg("获取成功");
        r.setSuccess(true);
        r.setData(roomList);

        return r;

    }


    /**
     * 添加房间
     */
    @ResponseBody
    @RequestMapping("addStoreRoom")
    @ApiOperation(value="添加房间", notes="添加房间")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店Id", required = true, dataType = "Long"),

    })
    public Result addStoreRoom( StoreRoom storeRoom){
        Result r= new Result();

        if(null == storeRoom.getStoreId() || null == storeRoom.getRoomName() || "".equals(storeRoom.getRoomName()) || null == storeRoom.getRecordStatus()

        ){
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("非空字段不能为空");
            return r;
        }
        //判断该门店该房间是否重名
        StoreRoom condition=storeRoomService.getRoomForName(storeRoom.getRoomName(),storeRoom.getStoreId());
        if(null != condition){
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("该房间名已被使用");
            return r;

        }

        storeRoomService.doInsert(storeRoom);

        r.setMsgcode(StatusUtil.OK);
        r.setMsg("添加成功");
        r.setSuccess(true);

        return r;

    }
    /**
     * 修改房间信息
     */
    @ResponseBody
    @RequestMapping("updateRoomData")
    public Result updateRoomData(StoreRoom storeRoom){

        Result r= new Result();
        int i =storeRoomService.updateRoom(storeRoom);
        if(i != 1){
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("修改失败");
            r.setSuccess(true);
            return r;
        }

        r.setMsgcode(StatusUtil.OK);
        r.setMsg("修改成功");
        r.setSuccess(true);
        return r;


    }

    /**
     * 房间开启与关闭
     */
    @ResponseBody
    @RequestMapping("openAndClose")
    public Result openAndClose(StoreRoom storeRoom){

        Result r= new Result();
        storeRoom=storeRoomDao.getRoomForRecordId(storeRoom.getRecordId());

        if(storeRoom.getRecordStatus().equals("0")){
            storeRoom.setRecordStatus("1");
        }else{
            storeRoom.setRecordStatus("0");
        }
        int i =storeRoomService.updateRoom(storeRoom);
        if(i != 1){
            r.setMsgcode(StatusUtil.ERROR);
            r.setMsg("修改失败");
            r.setSuccess(true);
            return r;
        }

        r.setMsgcode(StatusUtil.OK);
        r.setMsg("修改成功");
        r.setSuccess(true);
        return r;


    }


}
