package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.PicturesDAO;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Pictures;
import com.hy.salon.basic.entity.Product;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.service.StuffService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/stuff")
@Api(value = "StuffController| 员工控制器")
public class StuffController extends SimpleCRUDController<Stuff> {

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "stuffService")
    private StuffService stuffService;


    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "salonService")
    private SalonService salonService;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;


    @Override
    protected BaseDAOWithEntity<Stuff> getCrudDao() {
        return stuffDao;
    }

    /**
     * 获取员工按美容院分类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getStuff")
    @ApiOperation(value="获取员工按美容院分类", notes="获取员工按美容院分类")
    public Result getStuff(){
        SystemUser user = authenticateService.getCurrentLogin();

        List<Salon> stuffList=salonService.getSalonForCreateId(user.getRecordId());
        Result r= new Result();
        JSONArray jsonArr=new JSONArray();
        if(!stuffList.isEmpty()){
            for(Salon s :stuffList){
               List<Stuff> stuff= stuffService.getStuffForStoreId(s.getRecordId());
                JSONObject jsonObj=new JSONObject();
                jsonObj.put("stuff",stuff);
                jsonObj.put("salonName",s.getSalonName());
                jsonArr.add(jsonObj);
            }


        }
        r.setData(jsonArr);
        r.setMsg("获取成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;
    }


    /**
     * 获取店长个人资料
     */
    @ResponseBody
    @RequestMapping(value="getStuffData",method = RequestMethod.GET)
    @ApiOperation(value="获取店长个人资料", notes="获取店长个人资料")
    public Result getStoreDetails() {
        Result r= new Result();
        try {
            JSONObject jsonObj=new JSONObject();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        Pictures pic=picturesDao.getPicturesForCondition(stuff.getRecordId(),new Byte("1"),new Byte("0"));

            jsonObj.put("pic",pic);
            jsonObj.put("stuff",stuff);
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        r.setData(jsonObj);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }
        return r;
    }

    /**
     * 修改个人资料
     */
    @ResponseBody
    @RequestMapping(value="updateStuffData",method = RequestMethod.GET)
    @ApiOperation(value="修改个人资料", notes="修改个人资料")
    public Result updateStuffData(Stuff condition) {
        Result r= new Result();
        try {

            stuffDao.update(condition);
            r.setMsg("修改成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);

        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }

}
