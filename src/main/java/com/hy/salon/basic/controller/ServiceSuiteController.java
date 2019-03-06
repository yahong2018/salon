package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.ServiceSuiteDAO;
import com.hy.salon.basic.entity.ServiceSuite;
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

@Controller
@RequestMapping("/hy/basic/serviceSuite")
@Api(value = "ServiceSuiteController| 套卡控制器")
public class ServiceSuiteController extends SimpleCRUDController<ServiceSuite> {


    @Resource(name = "ServiceSuiteDao")
    private ServiceSuiteDAO serviceSuiteDao;


    @Override
    protected BaseDAOWithEntity<ServiceSuite> getCrudDao() {
        return serviceSuiteDao;
    }


    @ResponseBody
    @RequestMapping("/addServiceSuite")
    @ApiOperation(value="添加套卡", notes="添加套卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "suiteName", value = "套卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "priceMarket", value = "市场价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "price", value = "优惠价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "timeCreate", value = "建档时间", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "timeExpired", value = "失效日期：如果为null，则表示无期限", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "套卡状态：0 正常   1.停用     2.失效（即已过了有效期）", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "简介", required = true, dataType = "String")
    })
    public Result addServiceSuite(ServiceSuite condition,String serviceList){
        Result r= new Result();
        int i =serviceSuiteDao.insert(condition);
        //插入套卡项目关联表
//        String[] str = serviceList.split(",");
//        for(String s:str){
//            ServiceSuiteItem serviceSuiteItem=new ServiceSuiteItem();
//
//
//
//        }



        return r;
    }


    @ResponseBody
    @RequestMapping("/updateServiceSuite")
    @ApiOperation(value="添加套卡", notes="添加套卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "suiteName", value = "套卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "priceMarket", value = "市场价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "price", value = "优惠价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "timeCreate", value = "建档时间", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "timeExpired", value = "失效日期：如果为null，则表示无期限", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "套卡状态：0 正常   1.停用     2.失效（即已过了有效期）", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "简介", required = true, dataType = "String")
    })
    public Result updateServiceSuite(){
        Result r= new Result();

        return r;
    }










}
