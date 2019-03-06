package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.ServiceSeriesDAO;
import com.hy.salon.basic.entity.ServiceSeries;
import com.hy.salon.basic.service.ServiceSeriesService;
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
import com.hy.salon.basic.vo.Result;

import javax.annotation.Resource;
import java.util.List;

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
    public Result queryServiceSeries(){
        Result r= new Result();
        SystemUser user = authenticateService.getCurrentLogin();

        List<ServiceSeries> seriesList=serviceSeriesDao.getServiceSeriesForCreateId(user.getRecordId());

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













}
