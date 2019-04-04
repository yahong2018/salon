package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.dao.ServiceSeriesDAO;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.ServiceSeries;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.service.ServiceSeriesService;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hy.salon.basic.vo.Result;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/serviceSeries")
@Api(value = "ServiceSeriesController| 类别控制器")
public class ServiceSeriesController extends SimpleCRUDController<ServiceSeries> {


    @Resource(name = "serviceSeriesDao")
    private ServiceSeriesDAO serviceSeriesDao;


    @Override
    protected BaseDAOWithEntity<ServiceSeries> getCrudDao() {
        return serviceSeriesDao;
    }

    @Resource(name = "serviceSeriesService")
    private ServiceSeriesService serviceSeriesService;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;


    @ResponseBody
    @RequestMapping("/addServiceSeries")
    @ApiOperation(value="添加类别", notes="添加类别/系列，如果是一级类别，parentId传0")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "seriesName", value = "类别名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "parentId", value = "父Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "状态： 0. 启用   1. 停用", required = true, dataType = "Byte")
    })
    public Result addServiceSeries(ServiceSeries condition){

        Result r= new Result();
//        SystemUser user = authenticateService.getCurrentLogin();
//        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
//        condition.setStoreId(stuff.getStoreId());
        int i=serviceSeriesDao.insert(condition);
        if (i == 0) {
            r.setMsg("插入失败");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;
        }
        r.setMsg("插入成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;

    }

    @ResponseBody
    @RequestMapping("/updateServiceSeries")
    @ApiOperation(value="修改类别", notes="修改一二级类别")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "seriesName", value = "类别名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "parentId", value = "父Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "状态： 0. 启用   1. 停用", required = true, dataType = "Byte")
    })
    public Result updateServiceSeries(ServiceSeries condition){
        Result r= new Result();

        int i=serviceSeriesDao.update(condition);
        if (i == 0) {
            r.setMsg("修改失败");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;
        }
        r.setMsg("修改成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;

    }

    @ResponseBody
    @RequestMapping("/deleteServiceSeries")
    @ApiOperation(value="删除类别", notes="删除一二级类别")
    @ApiImplicitParam(paramType="query", name = "recordId", value = "类别Id", required = true, dataType = "Long")
    public Result deleteServiceSeries(Long recordId){
        Result r= new Result();

        int i=serviceSeriesDao.deleteById(recordId);

        if (i == 0) {
            r.setMsg("删除失败");
            r.setMsgcode("1");
            r.setSuccess(false);
            return r;
        }
        r.setMsg("删除成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;

    }

    @ResponseBody
    @RequestMapping("/queryServiceSeries")
    @ApiOperation(value="查找一级类别", notes="查找一级类别")
    public Result queryServiceSeries(Long SalonId){
        Result r= new Result();
        List<ServiceSeries> seriesList=null;
        if(SalonId != null){
            seriesList=serviceSeriesDao.getServiceSeriesForCreateId(SalonId);
        }else{
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            seriesList=serviceSeriesDao.getServiceSeriesForCreateId(stuff.getStoreId());
        }


        r.setMsg("获取成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(seriesList);
        return r;
    }

    @ResponseBody
    @RequestMapping("/querySonServiceSeries")
    @ApiOperation(value="获取二级类别", notes="获取二级类别")
    @ApiImplicitParam(paramType="query", name = "recordId", value = "一级类别Id", required = true, dataType = "Long")
    public Result querySonServiceSeries(Long recordId){
        Result r= new Result();

        List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForId(recordId);
        r.setMsg("获取成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(serList);
        return r;


    }

    @ResponseBody
    @RequestMapping("/queryAllServiceSeries")
    @ApiOperation(value="获取类别", notes="以一级类别分组获取二级类别")
    public Result queryAllServiceSeries(){
        Result r= new Result();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(stuff.getStoreId());

        JSONArray jsonArr2=new JSONArray();

        for(ServiceSeries s:serList){
            List<ServiceSeries> sonSerList=serviceSeriesDao.getServiceSeriesForId(s.getRecordId());
            JSONObject jsonObj2=new JSONObject();

            jsonObj2.put("ServiceRecordId",s.getRecordId());
            jsonObj2.put("ServiceName",s.getSeriesName());
            jsonObj2.put("ServiceList",sonSerList);
            jsonArr2.add(jsonObj2);
        }

        r.setMsg("获取成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(jsonArr2);
        return r;


    }

    /**
     * pc获取类别
     */
    @ResponseBody
    @RequestMapping("/queryParentServiceSeries")
    @ApiOperation(value="查找一级类别", notes="查找一级类别")
    public Result queryParentServiceSeries(Long serviceSeriesId){
        Result r= new Result();
        JSONObject jsonObj=new JSONObject();

        ServiceSeries serviceSeries=serviceSeriesDao.getServiceForRecordId(serviceSeriesId);

        ServiceSeries parentSeries=serviceSeriesDao.getServiceForRecordId(serviceSeries.getParentId());

        List<ServiceSeries> seriesList=serviceSeriesDao.getServiceSeriesForCreateId(new Long(2));

        List<ServiceSeries> sonSeriesList= serviceSeriesDao.getServiceSeriesForId(serviceSeries.getParentId());


        jsonObj.put("seriesList",seriesList);

        jsonObj.put("sonSeriesList",sonSeriesList);

        jsonObj.put("parentSeries",serviceSeries.getParentId());

        r.setMsg("获取成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(jsonObj);
        return r;
    }

    /**
     * pc获取类别
     */
    @ResponseBody
    @RequestMapping("/queryParentSeries")
    @ApiOperation(value="查找一级类别", notes="查找一级类别")
    public Result queryParentSeries(Long recordId){
        Result r= new Result();
        JSONObject jsonObj=new JSONObject();

        List<ServiceSeries> seriesList=serviceSeriesDao.getServiceSeriesForCreateId(recordId);

        jsonObj.put("seriesList",seriesList);

        r.setMsg("获取成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(jsonObj);
        return r;
    }














}
