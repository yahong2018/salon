package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.PicturesDAO;
import com.hy.salon.basic.dao.ServiceDAO;
import com.hy.salon.basic.dao.ServiceSeriesDAO;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/service")
@Api(value = "ServiceController| 次卡/服务项目控制器")
public class ServiceController extends SimpleCRUDController<Service> {


    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "serviceSeriesDao")
    private ServiceSeriesDAO serviceSeriesDao;


    @Override
    protected BaseDAOWithEntity<Service> getCrudDao() {
        return serviceDao;
    }


    @ResponseBody
    @RequestMapping("/queryService")
    @ApiOperation(value="获取所有次卡", notes="获取本美容院所有次卡")
   public Result queryService(Long storeId,int page){
        Result r= new Result();




        if(null == storeId ){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            storeId=stuff.getStoreId();
        }


        r.setTotal(serviceDao.queryServiceForId(storeId).size());
        PageHelper.startPage(page, 10);
        List<Service> serviceList= serviceDao.queryServiceForId(storeId);

//        serviceDao.

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(serviceList);
        return r;
    }

    @ResponseBody
    @RequestMapping("/queryServiceData")
    @ApiOperation(value="获取次卡信息", notes="通过次卡号获取次卡信息")
    public Result queryServiceData(Long recordId){
        Result r= new Result();
        Service service=serviceDao.queryOneService(recordId);
        JSONObject jsonObj=new JSONObject();
        List<Pictures> piclist= picturesDao.getPicturesForCondition(service.getRecordId(),new Byte("2"),new Byte("0"));
        ServiceSeries sonSeries=serviceSeriesDao.getServiceForRecordId(service.getServiceSeriesId());
        ServiceSeries parentSeries=serviceSeriesDao.getServiceForRecordId(sonSeries.getParentId());


        jsonObj.put("sonSeries",sonSeries);
        jsonObj.put("parentSeries",parentSeries);
        jsonObj.put("service",service);
        jsonObj.put("piclist",piclist);

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(jsonObj);
        return r;
    }
    @ResponseBody
    @RequestMapping("/addService")
    @ApiOperation(value="添加次卡", notes="添加次卡")
    public Result addService(HttpServletRequest request, Service condition, String picIdList,String comeFrom){


        if("PC".equals(comeFrom)){
            String  vs =  request.getParameter("condition");
            condition =  JSONObject.parseObject(vs, Service.class);
        }

        Result r= new Result();
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

        condition.setStoreId(stuff.getStoreId());


        if(condition.getCardType() == 0){
            condition.setPriceMarket(new Double(-1));
            condition.setTimeTotal(-1);
        }else{
            condition.setPricePerTime(new Double(-1));
        }

        int i=serviceDao.insert(condition);
        if(i!=1){
            r.setMsg("添加失败");
            r.setMsgcode("200");
            r.setSuccess(false);
            return r;
        }
        if(null != picIdList && !"".equals(picIdList)){
            //插入照片关联
            String[] str = picIdList.split(",");
            for(String s:str){
                Pictures pic= picturesDao.getPicForRecordId(Long.parseLong(s));
                if(null != pic){
                    pic.setMasterDataId(condition.getRecordId());
                    picturesDao.update(pic);
                }
            }
        }



        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;
    }
    @ResponseBody
    @RequestMapping("/updateService")
    @ApiOperation(value="修改次卡", notes="修改次卡")
    public Result updateService(Service condition,String picIdList,String deletePicList){
        Result r= new Result();
        int i=serviceDao.update(condition);
        if(i!=1){
            r.setMsg("修改失败");
            r.setMsgcode("200");
            r.setSuccess(false);
            return r;
        }
        if(null != picIdList && !"".equals(picIdList)){
            //插入照片关联
            String[] str = picIdList.split(",");
            for(String s:str){
                Pictures pic= picturesDao.getPicForRecordId(Long.parseLong(s));
                if(null != pic){
                    pic.setMasterDataId(condition.getRecordId());
                    picturesDao.update(pic);
                }
            }
        }

        if(null != deletePicList && !"".equals(deletePicList)){
            //删除照片关联
            String[] str2=deletePicList.split(",");
            for(String s:str2){
                Pictures pic= picturesDao.getPicForRecordId(Long.parseLong(s));
                if(null != pic){
                    picturesDao.delete(pic);
                }
            }
        }



        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;
    }

    @ResponseBody
    @RequestMapping(value="queryServiceForSeries",method = RequestMethod.GET)
    @ApiOperation(value="获取次卡通过项目分类", notes="获取次卡通过项目分类")
    public Result queryServiceForSeries(Long recordId){
        Result r= new Result();
//        SystemUser user = authenticateService.getCurrentLogin();
//        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForstoreId(recordId);

        JSONArray jsonArr=new JSONArray();
        for(ServiceSeries s:serList){
            JSONObject jsonObj=new JSONObject();
            jsonObj.put("seriesName",s.getSeriesName());
            List<Service> serviceList=serviceDao.queryServiceForServiceId(s.getRecordId(),recordId);
            for(Service ss:serviceList){
                List<Pictures> piclist= picturesDao.getPicturesForCondition(ss.getRecordId(),new Byte("2"),new Byte("0"));
                if(null != piclist && piclist.size()!=0){
                    ss.setPicUrl(piclist.get(0).getPicUrl());
                }

            }
            jsonObj.put("seriesList",serviceList);
            jsonArr.add(jsonObj);
        }

        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        r.setData(jsonArr);
        return r;
    }


    @ResponseBody
    @RequestMapping("deleteSeries")
    @ApiOperation(value="获取次卡通过项目分类", notes="获取次卡通过项目分类")
    public Result deleteSeries(Long recordId){
        Result r= new Result();
        serviceDao.deleteById(recordId);
        r.setMsg("删除成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;
    }

    @RequestMapping("batchDelete")
    @ResponseBody
    public Result batchDelete(@RequestBody Long[] recordIdList) {
        Result r= new Result();
        for(Long recordId:recordIdList){
            serviceDao.deleteById(recordId);
        }
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }

}
