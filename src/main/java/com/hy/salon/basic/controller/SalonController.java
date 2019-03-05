package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.service.SalonService;
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
import java.util.List;

@Controller
@Api(value = "SalonController|美容院控制器")
@RequestMapping("/hy/basic/salon")
public class SalonController extends SimpleCRUDController<Salon> {

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    @Override
    protected BaseDAOWithEntity<Salon> getCrudDao() {
        return salonDao;
    }
    @Resource(name = "salonService")
    private SalonService salonService;





    @RequestMapping("/getSalonData")
    @ResponseBody
    @ApiOperation(value="获取美容院信息", notes="获取美容院信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "美容院Id", required = true, dataType = "Long"),
    })
    public Result getSalonData( String recordId)  {
        Result r= new Result();
        if( null == recordId || "".equals(recordId)){
            r.setMsg("美容院号不能为空");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;
        }
        Salon salon=salonService.getSalonForId(recordId);
        if(null == salon ){
            r.setMsg("没有该美容院");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;
        }

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(salon);
        return r;

    }

    @ResponseBody
    @RequestMapping("getStoreData")
    @ApiOperation(value="获取门店列表", notes="获取门店列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "美容院Id", required = true, dataType = "Long"),
    })
    public Result getStireData(String recordId) {
        Result r= new Result();
        if( null == recordId || "".equals(recordId)){
            r.setMsg("美容院号不能为空");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;
        }
        List<Salon> StoreList=salonService.getSalonForStoreId(recordId);

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(StoreList);

       return r;
    }
    /**
     * 获取门店信息
     */
    @ResponseBody
    @RequestMapping("getStoreDetails")
    @ApiOperation(value="获取门店信息", notes="获取门店信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店Id", required = true, dataType = "Long"),
    })
    public Result getStoreDetails(String recordId) {
        Result r= new Result();
        if( null == recordId || "".equals(recordId)){
            r.setMsg("门店号不能为空");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;

        }

        Salon store=salonService.getSalonForId(recordId);

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(store);
        return r;


    }

    /**
     * 修改美容院/门店信息
     */
    @ResponseBody
    @RequestMapping("updateSalon")
    @ApiOperation(value="修改美容院/门店信息", notes="修改美容院/门店信息")
    public Result updateSalon(Salon condition){

        salonService.insert(condition);
        Result r= new Result();
        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);

        return r;
    }




}
