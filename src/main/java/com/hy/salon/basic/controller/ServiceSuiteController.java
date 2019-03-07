package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.ServiceDAO;
import com.hy.salon.basic.dao.ServiceSuiteDAO;
import com.hy.salon.basic.dao.ServiceSuiteItemDAO;
import com.hy.salon.basic.entity.ServiceSuite;
import com.hy.salon.basic.entity.ServiceSuiteItem;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.ServiceVo;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
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
@RequestMapping("/hy/basic/serviceSuite")
@Api(value = "ServiceSuiteController| 套卡控制器")
public class ServiceSuiteController extends SimpleCRUDController<ServiceSuite> {


    @Resource(name = "ServiceSuiteDao")
    private ServiceSuiteDAO serviceSuiteDao;

    @Resource(name= "serviceSuiteItemDao")
    private ServiceSuiteItemDAO serviceSuiteItemDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;
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
            @ApiImplicitParam(paramType="query", name = "description", value = "简介", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "serviceList", value = "绑定的项目（项目号用逗号隔开）", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "times", value = "次数", required = true, dataType = "int")
    })
    public Result addServiceSuite(ServiceSuite condition,String serviceList,Integer times){
        Result r= new Result();
        try {
        serviceSuiteDao.insert(condition);
        //插入套卡项目关联表
        String[] str = serviceList.split(",");
        for(String s:str){
            ServiceSuiteItem serviceSuiteItem=new ServiceSuiteItem();
            serviceSuiteItem.setTimes(times);
            serviceSuiteItem.setServiceSuiteId(condition.getRecordId());
            serviceSuiteItem.setServiceId(Long.parseLong(s));
            serviceSuiteItemDao.insert(serviceSuiteItem);
        }
            r.setMsg("插入成功");
            r.setSuccess(true);
            r.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }


        return r;
    }


    @ResponseBody
    @RequestMapping("/updateServiceSuite")
    @ApiOperation(value="修改套卡", notes="修改套卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "suiteName", value = "套卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "priceMarket", value = "市场价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "price", value = "优惠价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "timeCreate", value = "建档时间", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "timeExpired", value = "失效日期：如果为null，则表示无期限", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "套卡状态：0 正常   1.停用     2.失效（即已过了有效期）", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "简介", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "serviceList", value = "绑定的项目（项目号用逗号隔开）", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "times", value = "次数", required = true, dataType = "int")
    })
    public Result updateServiceSuite(ServiceSuite condition,String serviceList,Integer times){
        Result r= new Result();
        try {
            serviceSuiteDao.update(condition);

            String[] str = serviceList.split(",");
            //删除旧绑定，重新绑定
            List<ServiceSuiteItem> suitItem=serviceSuiteItemDao.querySuitItemForId(condition.getRecordId());
            if(!suitItem.isEmpty()){
                for(ServiceSuiteItem s:suitItem){
                    serviceSuiteItemDao.delete(s);
                }
            }
            for(String s:str){
                ServiceSuiteItem serviceSuiteItem=new ServiceSuiteItem();
                serviceSuiteItem.setTimes(times);
                serviceSuiteItem.setServiceSuiteId(condition.getRecordId());
                serviceSuiteItem.setServiceId(Long.parseLong(s));
                serviceSuiteItemDao.insert(serviceSuiteItem);

            }

            r.setMsg("修改成功");
            r.setSuccess(true);
            r.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }


    @ResponseBody
    @RequestMapping("/queryServiceSuite")
    @ApiOperation(value="获取套卡列表", notes="获取套卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
    })
    public Result queryServiceSuite(){
        Result r= new Result();
        try {
            SystemUser user = authenticateService.getCurrentLogin();
            List<ServiceSuite> suiteList= serviceSuiteDao.querySuitItemForCreateId(user.getRecordId());


            r.setData(suiteList);
            r.setMsg("获取成功");
            r.setSuccess(true);
            r.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }


    @ResponseBody
    @RequestMapping("/queryServiceSuiteData")
    @ApiOperation(value="获取套卡详情", notes="通过Id获取套卡详情")
    public Result queryServiceSuiteData(Long recordId){
        Result r= new Result();
        try {
            ServiceSuite suite= serviceSuiteDao.querySuitItemDataForId(recordId);
            List<ServiceVo> serviceVo=serviceDao.getServiceForSuit(suite.getRecordId());


            Map dataMap =new HashMap<String, Object>();
            dataMap.put("serviceSuite",suite);
            dataMap.put("serviceVo",serviceVo);

            r.setData(dataMap);
            r.setMsg("获取成功");
            r.setSuccess(true);
            r.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }










}
