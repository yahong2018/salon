package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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




    /**
     * 获取美容院信息
     */
    @RequestMapping("/getSalonData")
    @ResponseBody
    public Result getSalonData(HttpServletResponse resp, String recordId)  {
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
    /**
     * 获取门店列表
     */
    @ResponseBody
    @RequestMapping("getStoreData")
    public Result getStireData(HttpServletResponse resp,String recordId) {
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
    public Result getStoreDetails(HttpServletResponse resp,String recordId) {
        Result r= new Result();
        if( null == recordId || "".equals(recordId)){
            r.setMsg("美容院号不能为空");
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
    @ApiOperation(value="修改美容院/门店信息", notes="a")
    public Result updateSalon(Salon condition){

        salonService.insert(condition);
        Result r= new Result();
        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);

        return r;
    }




}
