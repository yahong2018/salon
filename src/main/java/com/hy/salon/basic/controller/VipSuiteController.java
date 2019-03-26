package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.*;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.VipSuiteService;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.ServiceSeriesVo;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/vipSuite")
@Api(value = "VipSuiteController| 充值卡控制器")
public class VipSuiteController extends SimpleCRUDController<VipSuite> {

    @Resource(name = "vipSuiteDao")
    private VipSuiteDAO vipSuiteDao;

    @Resource(name = "vipSuiteService")
    private VipSuiteService vipSuiteService;

    @Resource(name = "vipSuiteItemDao")
    private VipSuiteItemDAO vipSuiteItemDao;

    @Resource(name = "serviceSeriesDao")
    private ServiceSeriesDAO serviceSeriesDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "vipSuiteItemDiscountRangeDAO")
    private VipSuiteItemDiscountRangeDAO vipSuiteItemDiscountRangeDAO;

    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;


    @Override
    protected BaseDAOWithEntity<VipSuite> getCrudDao() {
        return vipSuiteDao;
    }
    private final ListRequestProcessHandler listRequestProcessHandler = new ListRequestProcessHandler();
    @ResponseBody
    @RequestMapping("/getVipSuiteList")
    @ApiOperation(value="获取充值卡列表", notes="获取充值卡列表")
    public ExtJsResult getVipSuiteList(HttpServletRequest request, HttpServletResponse response){

        String store_id  = request.getParameter("store_id");
        if(StringUtils.isEmpty(store_id)){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            store_id = stuff.getStoreId()+"";
        }
       long storeId  = Long.valueOf(store_id).longValue();
        ExtJsResult VipSuiteList=vipSuiteService.getVipSuiteListIdSystem(storeId, request,new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return vipSuiteDao.getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return vipSuiteDao.getPageListCount(listRequest.toMap(), null);
            }
        });

        return VipSuiteList;
    }


    @ResponseBody
    @RequestMapping( "/addVipSuite")
    @ApiOperation(value="添加充值卡", notes="添加充值卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "suiteName", value = "充值卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "price", value = "面额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "vipSuiteStatus", value = "记录状态：0.启用   1.停用", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "介绍", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "bindingJson", value = "绑定json", required = true, dataType = "String"),

    })
    @Transactional(rollbackFor = Exception.class)
    public Result addVipSuite(HttpServletRequest request, VipSuite condition, String bindingJson, String picIdList){
        Result r= new Result();
        String  vs =  request.getParameter("condition");
        condition =  JSONObject.parseObject(vs,VipSuite.class);
        //先写死，后面改
//        String  bindingJson="[{\"recordType\": 0,\"discount\": 8,\"itemId\": \"1,2,3\"}, {\"recordType\": 1,\"discount\": 8,\"itemId\": \"3,4,5\"}, {\"recordType\": 2,\"discount\": 8,\"itemId\": \"6,7,8\"}]";
        try {
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            condition.setStoreId(stuff.getStoreId());
            int ii =vipSuiteDao.insert(condition);
            if(ii != 0){
                //解析绑定json，绑定关系
                JSONArray jsonArr=JSONArray.parseArray(bindingJson);
                if(!jsonArr.isEmpty()){
                    for(int i=0;i<jsonArr.size();i++){
                        JSONObject jsonObj=jsonArr.getJSONObject(i);
                        String recordType=jsonObj.getString("recordType");
                        String itemId=jsonObj.getString("serviceId");
                        String discount=jsonObj.getString("discount");

                        //绑定父类
                        VipSuiteItem vipSuitItem=new VipSuiteItem();
                        vipSuitItem.setRecordType(Byte.parseByte(recordType));
                        vipSuitItem.setVipSuiteId(condition.getRecordId());
                        vipSuitItem.setDiscount(Byte.parseByte(discount));
                        vipSuiteItemDao.insert(vipSuitItem);



                        String[] str = itemId.split(",");
                        for(String s :str){

                            VipSuiteItemDiscountRange vipRangeCondition=new VipSuiteItemDiscountRange();
                            vipRangeCondition.setServiceId(Long.parseLong(s));
                            vipRangeCondition.setVipSuiteItemId(vipSuitItem.getRecordId());
                            vipSuiteItemDiscountRangeDAO.insert(vipRangeCondition);
                        }
                    }

                }

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
    @RequestMapping("/updateVipSuite")
    @ApiOperation(value="编辑充值卡", notes="编辑充值卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "suiteName", value = "充值卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "price", value = "面额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "vipSuiteStatus", value = "记录状态：0.启用   1.停用", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "介绍", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "bindingJson", value = "绑定json", required = true, dataType = "String"),

    })
    public Result updateVipSuite(VipSuite condition,String bindingJson,String picIdList,HttpServletRequest request){
        Result r= new Result();
        //先写死，后面改
//        String  bindingJson="[{\"recordType\": 0,\"discount\": 8,\"itemId\": \"12,13,14\"}, {\"recordType\": 1,\"discount\": 8,\"itemId\": \"20,24,25\"}, {\"recordType\": 2,\"discount\": 8,\"itemId\": \"36,30,31\"}]";
        try {

            String  vs =  request.getParameter("condition");
            condition =  JSONObject.parseObject(vs,VipSuite.class);
            int ii =vipSuiteDao.update(condition);
            if(ii != 0){
                //删除绑定的项目
                List<VipSuiteItem> suiteItemList= vipSuiteItemDao.queryVipSuitForId(condition.getRecordId());
                if(!suiteItemList.isEmpty()){
                    for(VipSuiteItem vsi:suiteItemList){
                        long id =  vsi.getRecordId();
                        String where = " where vip_suite_item_id=#{vipSuiteItemId} ";
                        Map parameters = new HashMap();
                        parameters.put("vipSuiteItemId",id);
                        vipSuiteItemDiscountRangeDAO.deleteByWhere(where,parameters);
                        vipSuiteItemDao.delete(vsi);
                    }}



                //解析绑定json，绑定关系
                JSONArray jsonArr=JSONArray.parseArray(bindingJson);
                if(!jsonArr.isEmpty()){
                    for(int i=0;i<jsonArr.size();i++){
                        JSONObject jsonObj=jsonArr.getJSONObject(i);
                        String recordType=jsonObj.getString("recordType");
                        String itemId=jsonObj.getString("itemId");
                        String discount=jsonObj.getString("discount");

                        //绑定父类
                        VipSuiteItem vipSuitItem=new VipSuiteItem();
                        vipSuitItem.setRecordType(Byte.parseByte(recordType));
                        vipSuitItem.setVipSuiteId(condition.getRecordId());
                        vipSuitItem.setDiscount(Byte.parseByte(discount));
                        vipSuiteItemDao.insert(vipSuitItem);

                        String[] str = itemId.split(",");
                        for(String s :str){
                            VipSuiteItemDiscountRange vipRangeCondition=new VipSuiteItemDiscountRange();
                            vipRangeCondition.setServiceId(Long.parseLong(s));
                            vipRangeCondition.setVipSuiteItemId(vipSuitItem.getRecordId());
                            vipSuiteItemDiscountRangeDAO.insert(vipRangeCondition);
                        }
                    }
                }
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
    @RequestMapping("/queryVipSuiteData")
    @ApiOperation(value="查询充值卡详情", notes="查询充值卡详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "id", required = true, dataType = "String")
    })
    public Result queryVipSuiteData(Long recordId){
        Result r= new Result();
        try {
            JSONObject jsonObj=new JSONObject();
            JSONArray jsonArr=new JSONArray();
            VipSuite vipSuit= vipSuiteDao.getVipSuiteForId(recordId);
            jsonObj.put("vipSuit",vipSuit);

            List<Map<String,String>> service1=vipSuiteDao.getServiceSeriesForVip(recordId,new Long(0));
            if(service1.size() != 0){
                jsonArr.add(service1);
            }
            List<Map<String,String>> service2=vipSuiteDao.getServiceSeriesForVip(recordId,new Long(1));
            if(service2.size() != 0){
                jsonArr.add(service2);
            }
            List<Map<String,String>> service3=vipSuiteDao.getServiceSeriesForVip(recordId,new Long(2));
            if(service3.size() != 0){
                jsonArr.add(service3);
            }

            List<Pictures> pic = picturesDao.getPicturesForCondition(recordId,new Byte("4") , new Byte("0"));
            jsonObj.put("picUrl",pic);
            jsonObj.put("serviceData",jsonArr);


            r.setData(jsonObj);
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
    @RequestMapping("/queryVipSuite")
    @ApiOperation(value="获取充值卡绑定的项目", notes="获取充值卡绑定的项目")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "recordType", value = "折扣类型", required = true, dataType = "Byte")
    })
    public Result queryVipSuite(Long recordId,Byte recordType){
        Result r= new Result();


        try {
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(stuff.getStoreId());
//            Map m2=new HashMap<String, Object>();
            JSONArray jsonArr=new JSONArray();
            List<ServiceSeriesVo> serSeries=new ArrayList<>();
            int i =0;
            for(ServiceSeries s:serList){
               serSeries=serviceSeriesDao.getServiceSeriesVo(s.getRecordId());
                List<ServiceSeriesVo>  bindingSer=serviceSeriesDao.getServiceSeriesVo(s.getRecordId(),recordId,recordType);
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


    @ResponseBody
    @RequestMapping("/deleteVipSuite")
    @ApiOperation(value="删除充值卡以及其绑定的项目", notes="以id删除充值卡已经其绑定的项目")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "id", required = true, dataType = "String"),
    })
    public Result deleteVipSuite(Long recordId){
        Result r= new Result();



        try {

            //删除绑定的项目
            List<VipSuiteItem> suiteItemList= vipSuiteItemDao.queryVipSuitForId(recordId);
            if(!suiteItemList.isEmpty()){
            for(VipSuiteItem vsi:suiteItemList){
               long id =  vsi.getRecordId();
                String where = " where vip_suite_item_id=#{vipSuiteItemId} ";
                Map parameters = new HashMap();
                parameters.put("vipSuiteItemId",id);
                vipSuiteItemDiscountRangeDAO.deleteByWhere(where,parameters);
                vipSuiteItemDao.delete(vsi);
            }}
            String where = " where master_data_id=#{masterDataId} ";
            Map parameters = new HashMap();
            parameters.put("masterDataId",recordId);
            picturesDao.deleteByWhere(where,parameters);
            vipSuiteDao.deleteById(recordId);
//            if(!suiteItemList.isEmpty()){
//                for(VipSuiteItem v:suiteItemList){
//                    vipSuiteItemDao.delete(v);
//                }
//            }



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


}
