package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.VipSuiteDAO;
import com.hy.salon.basic.entity.ServiceSuite;
import com.hy.salon.basic.entity.ServiceSuiteItem;
import com.hy.salon.basic.entity.VipSuite;
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
@RequestMapping("/hy/basic/vipSuite")
@Api(value = "VipSuiteController| 充值卡控制器")
public class VipSuiteController extends SimpleCRUDController<VipSuite> {

    @Resource(name = "vipSuiteDao")
    private VipSuiteDAO vipSuiteDao;

    @Override
    protected BaseDAOWithEntity<VipSuite> getCrudDao() {
        return vipSuiteDao;
    }



    @ResponseBody
    @RequestMapping("/addVipSuite")
    @ApiOperation(value="添加充值卡", notes="添加充值卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "suite_name", value = "充值卡名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "price", value = "面额", required = true, dataType = "double"),
            @ApiImplicitParam(paramType="query", name = "vip_suite_status", value = "记录状态：0.启用   1.停用", required = true, dataType = "Byte"),
            @ApiImplicitParam(paramType="query", name = "description", value = "介绍", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "binding", value = "失效日期：如果为null，则表示无期限", required = true, dataType = "String"),

    })
    public Result addVipSuite(ServiceSuite condition, String serviceList, Integer times){
        Result r= new Result();
        try {

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



    public static void main(String[] args) {
        String s="[{\"record_type\":0,\"item_id\":\"1,2,3\"},{\"record_type\":1,\"item_id\":\"3,4,5\"},{\"record_type\":1,\"item_id\":\"6,7,8\"}]";
        JSONArray jsonArr=JSONArray.parseArray(s);
        for(int i =0 ; i<jsonArr.size(); i++){
            JSONObject jsonObj=jsonArr.getJSONObject(i);
            System.out.println("recordType======"+jsonObj.getString("record_type"));
            System.out.println("itemId======"+jsonObj.getString("item_id"));

        }



    }


}
