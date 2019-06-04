package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.ServiceSeriesVo;
import com.hy.salon.basic.vo.ServiceVo;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.misc.SessionManager;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "serviceSeriesDao")
    private ServiceSeriesDAO serviceSeriesDao;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Override
    protected BaseDAOWithEntity<ServiceSuite> getCrudDao() {
        return serviceSuiteDao;
    }


    @ResponseBody
    @RequestMapping("addServiceSuite")
    @ApiOperation(value="添加套卡", notes="添加套卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "suiteName", value = "套卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "priceMarket", value = "市场价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "price", value = "优惠价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "timeCreate", value = "建档时间", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "timeExpired", value = "失效日期：如果为null，则表示无期限", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "套卡状态：0 正常   1.停用     2.失效（即已过了有效期）", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "简介", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "bindingJson", value = "绑定JSON", required = true, dataType = "String")

    })
    @Transactional(rollbackFor = Exception.class)
    public Result addServiceSuite(HttpServletRequest request, ServiceSuite condition, String picIdList, String bindingJson,String comeFrom){
        HttpSession session= SessionManager.getCurrentSession();
        synchronized (session) {
            Result r = new Result();
            try {

                if ("PC".equals(comeFrom)) {
                    String vs = request.getParameter("condition");
                    condition = JSONObject.parseObject(vs, ServiceSuite.class);
                }
                //先写死，后面改
//            String bindingJson="[{\"serviceId\": 1,\"times\": 10},{\"serviceId\": 5,\"times\": 10},{\"serviceId\": 8,\"times\": 10}]";
                SystemUser user = authenticateService.getCurrentLogin();
                Stuff stuff = stuffDao.getStuffForUser(user.getRecordId());
                condition.setStoreId(stuff.getStoreId());
                condition.setTimeCreate(new Date());
                int ii = serviceSuiteDao.insert(condition);
                if (ii != 0) {
                    //解析绑定json，绑定关系
                    JSONArray jsonArr = JSONArray.parseArray(bindingJson);
                    if (jsonArr != null) {
                        for (int i = 0; i < jsonArr.size(); i++) {
                            JSONObject jsonObj = jsonArr.getJSONObject(i);
                            String serviceId = jsonObj.getString("serviceId");
                            String times = jsonObj.getString("times");
                            ServiceSuiteItem serviceSuiteItem = new ServiceSuiteItem();
                            serviceSuiteItem.setTimes(Integer.parseInt(times));
                            serviceSuiteItem.setServiceSuiteId(condition.getRecordId());
                            serviceSuiteItem.setServiceId(Long.parseLong(serviceId));
                            serviceSuiteItemDao.insert(serviceSuiteItem);

                        }
                    }
                }

                //插入照片关联
                if (null != picIdList && !"".equals(picIdList)) {
                    String[] str = picIdList.split(",");
                    for (String s : str) {
                        Pictures pic = picturesDao.getPicForRecordId(Long.parseLong(s));
                        if (null != pic) {
                            pic.setMasterDataId(condition.getRecordId());
                            picturesDao.update(pic);
                        }
                    }
                }
                r.setMsg("插入成功");
                r.setSuccess(true);
                r.setMsgcode(StatusUtil.OK);
            } catch (Exception e) {
                e.printStackTrace();
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
            }


            return r;
        }
    }


    @ResponseBody
    @RequestMapping("/updateServiceSuite")
    @ApiOperation(value="修改套卡", notes="修改套卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "storeId", value = "所属门店", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "suiteName", value = "套卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "priceMarket", value = "市场价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "price", value = "优惠价格", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "timeCreate", value = "建档时间", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "timeExpired", value = "失效日期：如果为null，则表示无期限", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "recordStatus", value = "套卡状态：0 正常   1.停用     2.失效（即已过了有效期）", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "简介", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "bindingJson", value = "绑定JSON", required = true, dataType = "String")
    })
    @Transactional(rollbackFor = Exception.class)
    public Result updateServiceSuite(ServiceSuite condition,String picIdList,String deletePicList,String comeFrom, String bindingJson,HttpServletRequest request){
        Result r= new Result();
        try {
            if("PC".equals(comeFrom)){
                String  vs =  request.getParameter("condition");
                condition =  JSONObject.parseObject(vs, ServiceSuite.class);
            }

            //先写死，后面改
//            String bindingJson="[{\"serviceId\":5,\"times\": 12},{\"serviceId\": 6,\"times\": 12},{\"serviceId\": 7,\"times\": 12}]";
            int ii =serviceSuiteDao.update(condition);
            if(ii!=0){
                //删除旧绑定，重新绑定
                List<ServiceSuiteItem> suitItem=serviceSuiteItemDao.querySuitItemForId(condition.getRecordId());
                if(!suitItem.isEmpty()){
                    for(ServiceSuiteItem s:suitItem){
                        serviceSuiteItemDao.delete(s);
                    }
                }
                //解析绑定json，绑定关系
                JSONArray jsonArr=JSONArray.parseArray(bindingJson);
                if(jsonArr != null){
                    for(int i=0;i<jsonArr.size();i++){
                        JSONObject jsonObj=jsonArr.getJSONObject(i);
                        String serviceId=jsonObj.getString("serviceId");
                        String times=jsonObj.getString("times");
                        ServiceSuiteItem serviceSuiteItem=new ServiceSuiteItem();
                        serviceSuiteItem.setTimes(Integer.parseInt(times));
                        serviceSuiteItem.setServiceSuiteId(condition.getRecordId());
                        serviceSuiteItem.setServiceId(Long.parseLong(serviceId));
                        serviceSuiteItemDao.insert(serviceSuiteItem);
                    }
                }
            }

                if(null!=picIdList && !"".equals(picIdList)){
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
            if(null!=deletePicList && !"".equals(deletePicList)){
                //删除照片关联
                String[] str2=deletePicList.split(",");
                for(String s:str2){
                    Pictures pic= picturesDao.getPicForRecordId(Long.parseLong(s));
                    if(null != pic){
                        picturesDao.delete(pic);
                    }
                }
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
    public Result queryServiceSuite(Long storeId,int page){
        Result r= new Result();
        try {

            if(null == storeId ){
                SystemUser user = authenticateService.getCurrentLogin();
                Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
                storeId=stuff.getStoreId();
            }

            r.setTotal(serviceSuiteDao.querySuitItemForCreateId(storeId).size());
            PageHelper.startPage(page, 10);
            List<ServiceSuite> suiteList= serviceSuiteDao.querySuitItemForCreateId(storeId);
            if(null != suiteList && suiteList.size()!=0){
                for(ServiceSuite s:suiteList){
                if(new Date().getTime()>s.getTimeExpired().getTime()){
                    s.setIsExpired("1");
                }else{
                    s.setIsExpired("0");
                }
                    List<Pictures> pic=picturesDao.getPicturesForCondition(s.getRecordId(),new Byte("3"),new Byte("0"));
                    if(null!=pic && pic.size()!=0){
                        s.setPicUrl(pic.get(0).getPicUrl());
                    }
                }
            }



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
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
    })
    public Result queryServiceSuiteData(Long recordId){
        Result r= new Result();
        try {
            ServiceSuite suite= serviceSuiteDao.querySuitItemDataForId(recordId);
            List<Pictures> piclist= picturesDao.getPicturesForCondition(recordId,new Byte("3"),new Byte("0"));


            List<Map<String,Object> > dataList=new ArrayList();
            List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(suite.getStoreId());

            List<Map<String,Object>> binServiceList=serviceDao.queryBinServiceForSeries(recordId);

           for(ServiceSeries s:serList){
                List<Map<String,Object>> serviceList=serviceDao.queryServiceForSeries(s.getRecordId());
                for(Map<String,Object> m:serviceList){
                    dataList.add(m);
                    m.put("isBin","0");
                    m.put("times","");
                }
            }

            for(Map<String,Object> m:dataList){
                for(Map<String,Object> mm:binServiceList){
                    if(m.get("recordId") == mm.get("recordId")){
                        String str=mm.get("recordId").toString();
                        if(str!=null){
                            ServiceSuiteItem serviceSuiteItem =serviceSuiteItemDao.querySuitItemForId(recordId,str);
                            m.put("times",serviceSuiteItem.getTimes().toString());
                        }
                        m.put("isBin","1");
                    }

                }
            }
            List<Map<String,Object> > dataList2=new ArrayList();
            for(Map<String,Object> m:dataList){
                if(m.get("isBin").equals("1")){
                    dataList2.add(m);
                }
            }


            Map dataMap =new HashMap<String, Object>();
            dataMap.put("dataList",dataList2);
            dataMap.put("serviceSuite",suite);
            dataMap.put("piclist",piclist);

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


    @ResponseBody
    @RequestMapping("/deleteSuitData")
    @ApiOperation(value="删除套卡详情", notes="通过Id删除套卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
    })
    public Result deleteSuitData(Long recordId){
        Result r= new Result();
        try {
            serviceSuiteDao.deleteById(recordId);
            //删除绑定关系
            List<ServiceSuiteItem> itemList=serviceSuiteItemDao.querySuitItemForId(recordId);
            if(!itemList.isEmpty()){
                for(ServiceSuiteItem s:itemList){
                    serviceSuiteItemDao.delete(s);

                }

            }
            r.setMsg("删除成功");
            r.setSuccess(true);
            r.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }


    /**
     * 获取已绑定该套卡的服务项目列表
     */
    @ResponseBody
    @RequestMapping("/queryBinServiceOld")
    @ApiOperation(value="获取已绑定该套卡的服务项目列表", notes="通过套卡Id获取已绑定该套卡的服务项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
    })
    public Result queryBinServiceOld(Long recordId){
        Result r= new Result();
        try {
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(stuff.getStoreId());

            JSONArray jsonArr=new JSONArray();
            List<ServiceSeriesVo> serSeries=new ArrayList<>();
            int i =0;
            for(ServiceSeries s:serList){
                serSeries=serviceSeriesDao.getServiceSeriesVo(s.getRecordId());
                List<ServiceSeriesVo>  bindingSer=serviceSeriesDao.getServiceSeriesVoForSuite(s.getRecordId(),recordId);
                for(ServiceSeriesVo ss :serSeries){
                    for(ServiceSeriesVo sss:bindingSer){
                        if(ss.getRecordId()==sss.getRecordId()){
                            ss.setIsChoice(1);
                        }
                    }
                }
                JSONObject jsonObj2=new JSONObject();
                jsonObj2.put("serviceName",s.getSeriesName());
                jsonObj2.put("serviceList",serSeries);

                jsonArr.add(jsonObj2);

            }
            r.setData(jsonArr);
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


    /**
     * 获取套卡的服务项目列表
     */
    @ResponseBody
    @RequestMapping("/queryService")
    @ApiOperation(value="获取套卡的服务项目列表", notes="通过套卡Id获取套卡的服务项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
    })
    public Result queryService(Long recordId,Long salonId){
        Result r= new Result();
        try {

            List dataList=new ArrayList();
            List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(salonId);

            for(ServiceSeries s:serList){
                List<Map<String,Object>> serviceList=serviceDao.queryServiceForSeries(s.getRecordId());
                for(Map<String,Object> m:serviceList){
                    dataList.add(m);
                }
            }
            r.setData(dataList);
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


    /**
     * 获取套卡的绑定服务项目列表
     */
    @ResponseBody
    @RequestMapping("/queryBinService")
    @ApiOperation(value="获取套卡的绑定服务项目列表", notes="通过套卡Id获取套卡的绑定服务项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
    })
    public Result queryBinService(Long recordId,Long salonId){
        Result r= new Result();
        try {

            List<Map<String,Object> > dataList=new ArrayList();
            List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(salonId);

            List<Map<String,Object>> binServiceList=serviceDao.queryBinServiceForSeries(recordId);

            for(ServiceSeries s:serList){
                List<Map<String,Object>> serviceList=serviceDao.queryServiceForSeries(s.getRecordId());
                for(Map<String,Object> m:serviceList){
                    dataList.add(m);
                    m.put("isBin","0");
                    m.put("times","");
                }
            }

            for(Map<String,Object> m:dataList){
                for(Map<String,Object> mm:binServiceList){
                    if(m.get("recordId") == mm.get("recordId")){
                        String str=mm.get("recordId").toString();
                        if(str!=null){
                            ServiceSuiteItem serviceSuiteItem =serviceSuiteItemDao.querySuitItemForId(recordId,str);
                            m.put("times",serviceSuiteItem.getTimes().toString());
                        }
                        m.put("isBin","1");
                    }

                }
            }


            r.setData(dataList);
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

    /**
     * 获取套卡的服务项目列表
     */
    @ResponseBody
    @RequestMapping("/queryServicePC")
    @ApiOperation(value="获取套卡的服务项目列表", notes="通过套卡Id获取套卡的服务项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
    })
    public Result queryServicePC(Long salonId){
        Result r= new Result();
        try {
            JSONArray jsonArr=new JSONArray();
            List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(salonId);

            for(ServiceSeries s:serList){
                List<Map<String,Object>> serviceList=serviceDao.queryServiceForSeries(s.getRecordId());
                JSONObject jsonObj=new JSONObject();
                jsonObj.put("ServiceRecordId",s.getRecordId());
                jsonObj.put("ServiceName",s.getSeriesName());
                jsonObj.put("ServiceList",serviceList);
                jsonArr.add(jsonObj);

            }
            r.setData(jsonArr);
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


    /**
     * 获取套卡的已经绑定服务项目列表
     */
    @ResponseBody
    @RequestMapping("/queryBinServicePC")
    @ApiOperation(value="获取套卡的绑定服务项目列表", notes="通过套卡Id获取套卡的绑定服务项目列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "Id", required = true, dataType = "Long"),
    })
    public Result queryBinServicePC(Long recordId,Long salonId){
        Result r= new Result();
        try {
            JSONArray jsonArr=new JSONArray();
            List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(salonId);

            List<Map<String,Object>> binServiceList=serviceDao.queryBinServiceForSeries(recordId);

            for(ServiceSeries s:serList){
                List<Map<String,Object>> serviceList=serviceDao.queryServiceForSeries(s.getRecordId());
                for(Map<String,Object> m:serviceList){

                    m.put("times","");
                    m.put("isBin","0");
                    for(Map<String,Object> mm:binServiceList){
                        if(m.get("recordId") == mm.get("recordId")){
                            System.out.println("recordId+++++++++++"+mm.get("recordId"));
                            String str=mm.get("recordId").toString();
                            if(str!=null){
                                ServiceSuiteItem serviceSuiteItem =serviceSuiteItemDao.querySuitItemForId(recordId,str);
                                m.put("times",serviceSuiteItem.getTimes().toString());
                            }

                            m.put("isBin","1");
                        }
                    }
                }
                JSONObject jsonObj=new JSONObject();
                jsonObj.put("ServiceRecordId",s.getRecordId());
                jsonObj.put("ServiceName",s.getSeriesName());
                jsonObj.put("ServiceList",serviceList);
                jsonArr.add(jsonObj);
            }



            r.setData(jsonArr);
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


    @RequestMapping("batchDelete")
    @ResponseBody
    public Result batchDelete(@RequestBody Long[] recordIdList) {
        Result r= new Result();
        for(Long recordId:recordIdList){
            serviceSuiteDao.deleteById(recordId);
            //删除旧绑定，重新绑定
            List<ServiceSuiteItem> suitItem=serviceSuiteItemDao.querySuitItemForId(recordId);
            if(!suitItem.isEmpty()){
                for(ServiceSuiteItem s:suitItem){
                    serviceSuiteItemDao.delete(s);
                }
            }
        }
        r.setMsg("请求成功");
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;


    }






}
