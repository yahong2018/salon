package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.ServiceSeriesDAO;
import com.hy.salon.basic.dao.VipSuiteDAO;
import com.hy.salon.basic.dao.VipSuiteItemDAO;
import com.hy.salon.basic.entity.ServiceSeries;
import com.hy.salon.basic.entity.VipSuite;
import com.hy.salon.basic.entity.VipSuiteItem;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.ServiceSeriesVo;
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
@RequestMapping("/hy/basic/vipSuite")
@Api(value = "VipSuiteController| 充值卡控制器")
public class VipSuiteController extends SimpleCRUDController<VipSuite> {

    @Resource(name = "vipSuiteDao")
    private VipSuiteDAO vipSuiteDao;

    @Resource(name = "vipSuiteItemDao")
    private VipSuiteItemDAO vipSuiteItemDao;

    @Resource(name = "serviceSeriesDao")
    private ServiceSeriesDAO serviceSeriesDao;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;



    @Override
    protected BaseDAOWithEntity<VipSuite> getCrudDao() {
        return vipSuiteDao;
    }



    @ResponseBody
    @RequestMapping("/addVipSuite")
    @ApiOperation(value="添加充值卡", notes="添加充值卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "suiteName", value = "充值卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "price", value = "面额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "vipSuiteStatus", value = "记录状态：0.启用   1.停用", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "介绍", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "bindingJson", value = "绑定json", required = true, dataType = "String"),

    })
    public Result addVipSuite(VipSuite condition){
        Result r= new Result();
        //先写死，后面改
        String  bindingJson="[{\"recordType\": 0,\"discount\": 8,\"itemId\": \"1,2,3\"}, {\"recordType\": 1,\"discount\": 8,\"itemId\": \"3,4,5\"}, {\"recordType\": 2,\"discount\": 8,\"itemId\": \"6,7,8\"}]";
        try {
            int ii =vipSuiteDao.insert(condition);
            if(ii != 0){
                //解析绑定json，绑定关系
                JSONArray jsonArr=JSONArray.parseArray(bindingJson);
                if(!jsonArr.isEmpty()){
                    for(int i=0;i<jsonArr.size();i++){
                        JSONObject jsonObj=jsonArr.getJSONObject(i);
                        String recordType=jsonObj.getString("recordType");
                        String itemId=jsonObj.getString("itemId");
                        String discount=jsonObj.getString("discount");

                        String[] str = itemId.split(",");
                        for(String s :str){
                            VipSuiteItem vipSuitItem=new VipSuiteItem();
                            vipSuitItem.setVipSuiteId(condition.getRecordId());
                            vipSuitItem.setRecordType(Byte.parseByte(recordType));
                            switch(recordType){
                                case "0":
                                    vipSuitItem.setDiscountTime(Byte.parseByte(discount));
                                    vipSuitItem.setDiscountPeriod(Byte.parseByte("0"));
                                    vipSuitItem.setDiscountProduction(Byte.parseByte("0"));
                                    break;
                                case "1":
                                    vipSuitItem.setDiscountTime(Byte.parseByte("0"));
                                    vipSuitItem.setDiscountPeriod(Byte.parseByte(discount));
                                    vipSuitItem.setDiscountProduction(Byte.parseByte("0"));
                                    break;
                                case "2":
                                    vipSuitItem.setDiscountTime(Byte.parseByte("0"));
                                    vipSuitItem.setDiscountPeriod(Byte.parseByte("0"));
                                    vipSuitItem.setDiscountProduction(Byte.parseByte(discount));
                                    break;
                            }
                            vipSuitItem.setItemId(Long.parseLong(s));
                            vipSuiteItemDao.insert(vipSuitItem);

                        }
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
    public Result updateVipSuite(VipSuite condition){
        Result r= new Result();
        //先写死，后面改
        String  bindingJson="[{\"recordType\": 0,\"discount\": 8,\"itemId\": \"12,13,14\"}, {\"recordType\": 1,\"discount\": 8,\"itemId\": \"20,24,25\"}, {\"recordType\": 2,\"discount\": 8,\"itemId\": \"36,30,31\"}]";
        try {
            int ii =vipSuiteDao.update(condition);
            if(ii != 0){
                //解除绑定关系，重新绑定
                List<VipSuiteItem> itemList= vipSuiteItemDao.queryVipSuitForId(condition.getRecordId());
                if(!itemList.isEmpty()){
                    for(VipSuiteItem v:itemList){
                        vipSuiteItemDao.delete(v);
                    }

                }

                //解析绑定json，绑定关系
                JSONArray jsonArr=JSONArray.parseArray(bindingJson);
                if(!jsonArr.isEmpty()){
                    for(int i=0;i<jsonArr.size();i++){
                        JSONObject jsonObj=jsonArr.getJSONObject(i);
                        String recordType=jsonObj.getString("recordType");
                        String itemId=jsonObj.getString("itemId");
                        String discount=jsonObj.getString("discount");

                        String[] str = itemId.split(",");
                        for(String s :str){
                            VipSuiteItem vipSuitItem=new VipSuiteItem();
                            vipSuitItem.setVipSuiteId(condition.getRecordId());
                            vipSuitItem.setRecordType(Byte.parseByte(recordType));
                            switch(recordType){
                                case "0":
                                    vipSuitItem.setDiscountTime(Byte.parseByte(discount));
                                    vipSuitItem.setDiscountPeriod(Byte.parseByte("0"));
                                    vipSuitItem.setDiscountProduction(Byte.parseByte("0"));
                                    break;
                                case "1":
                                    vipSuitItem.setDiscountTime(Byte.parseByte("0"));
                                    vipSuitItem.setDiscountPeriod(Byte.parseByte(discount));
                                    vipSuitItem.setDiscountProduction(Byte.parseByte("0"));
                                    break;
                                case "2":
                                    vipSuitItem.setDiscountTime(Byte.parseByte("0"));
                                    vipSuitItem.setDiscountPeriod(Byte.parseByte("0"));
                                    vipSuitItem.setDiscountProduction(Byte.parseByte(discount));
                                    break;
                            }
                            vipSuitItem.setItemId(Long.parseLong(s));
                            vipSuiteItemDao.insert(vipSuitItem);
                        }
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
            VipSuite vipSuit= vipSuiteDao.getVipSuiteForId(recordId);


            r.setData(vipSuit);
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
            @ApiImplicitParam(paramType="query", name = "recordId", value = "id", required = true, dataType = "String")
    })
    public Result queryVipSuite(Long recordId,Byte recordType){
        Result r= new Result();


        try {
            SystemUser user = authenticateService.getCurrentLogin();
            List<ServiceSeries> serList=serviceSeriesDao.getServiceSeriesForCreateId(user.getRecordId());
            Map m=new HashMap<String, Object>();
            VipSuite vipSuit= vipSuiteDao.getVipSuiteForId(recordId);

            for(ServiceSeries s:serList){
                List<ServiceSeries> serSeries=serviceSeriesDao.getServiceSeriesForId(s.getRecordId());
                List<ServiceSeriesVo>  bindingSer=serviceSeriesDao.getServiceSeriesVo(s.getRecordId(),recordId,recordType);


            }




            r.setData(vipSuit);
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






    public static void main(String[] args) {
        String s="[{\"recordType\": 0,\"discount\": 8,\"itemId\": \"1,2,3\"}, {\"recordType\": 1,\"discount\": 8,\"itemId\": \"3,4,5\"}, {\"recordType\": 1,\"discount\": 8,\"itemId\": \"6,7,8\"}]";
        JSONArray jsonArr=JSONArray.parseArray(s);
        for(int i =0 ; i<jsonArr.size(); i++){
            JSONObject jsonObj=jsonArr.getJSONObject(i);
            System.out.println("recordType======"+jsonObj.getString("recordType"));
            System.out.println("itemId======"+jsonObj.getString("itemId"));

        }



    }


}