package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.dao.RetroactiveDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.AttendanceSheet;
import com.hy.salon.basic.entity.Retroactive;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.service.AttendanceSheetService;
import com.hy.salon.basic.service.RetroactiveService;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.util.GaoDeUtil;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/attendance")
@Api(value = "AttendanceController| 签到控制器")
public class AttendanceController {

    @Resource(name = "attendanceSheetService")
    private AttendanceSheetService attendanceSheetService;

    @Resource(name = "salonService")
    private SalonService salonService;

    @Resource(name = "retroactiveDao")
    private RetroactiveDao retroactiveDao;

    @Resource(name = "retroactiveService")
    private RetroactiveService retroactiveService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;


    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;
    /**
     * 考勤签到例子
     */


//    @RequestMapping("SignInDemo")
//    public void SignInDemo(HttpServletResponse resp){
//        AttendanceSheet condition=new AttendanceSheet();
//        condition.setStuffId(new Long(1));
//        condition.setAttendanceTime(new Date());
//        condition.setAddress("常平店");
//
//
//        attendanceSheetService.insert(condition);
//        JSONObject jsonObj=new JSONObject();
//        jsonObj.put("respCode","0000");
//
//        write(jsonObj,resp);
//    }


    /**
     * 补卡列表
     */
    @ResponseBody
    @RequestMapping(value = "getPatchCardList")
    @ApiOperation(value="补卡列表", notes="补卡列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "storeId", value = "门店id", required = true, dataType = "Long")
    })
    public ExtJsResult getPatchCardList(HttpServletRequest request,Long storeId){
        Result result  = new Result();
        if(storeId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
            storeId= stuff2.getStoreId();
        }

        ExtJsResult attendanceList = retroactiveService.getPatchCardListByStoreId(request, storeId, new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return retroactiveDao.getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return retroactiveDao.getPageListCount(listRequest.toMap(), null);
            }
        });

        return  attendanceList;
    }

    /**
     * 审核补卡
     */
    @ResponseBody
    @RequestMapping(value = "examinePatchCard")
    @ApiOperation(value="补卡", notes="补卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "auditOpinion", value = "审核意见", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "userId", value = "审核人", required = false, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "recordId", value = "补卡id", required = true, dataType = "Long"),
    })
    public Result examinePatchCard(String auditOpinion,long userId,long recordId ){
        if(userId==0){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff2 = stuffDao.getStuffForUser(user.getRecordId());
            userId= stuff2.getRecordId();
        }
        Retroactive retroactive  = retroactiveDao.getById(recordId);
        retroactive.setAuditStatu((byte) 1);
        retroactive.setAuditOpinion(auditOpinion);
        retroactive.setUserId(userId);
        Result result  = new Result();
        retroactiveDao.update(retroactive);
        result.setMsg("审核成功");
        result.setMsgcode("0");
        result.setSuccess(true);
        return  result;
    }


    /**
     * 补卡
     */
    @ResponseBody
    @RequestMapping(value = "patchCard")
    @ApiOperation(value="补卡", notes="补卡")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "auditPerson", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "date", value = "补卡日期", required = true, dataType = "Date"),
            @ApiImplicitParam(paramType="query", name = "reson", value = "补卡原因", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "auditStatu", value = "审核状态", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "salonId", value = "门店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "retroactiveType", value = "补卡类型", required = true, dataType = "int"),
    })
    public Result patchCard(Retroactive retroactive){
        Result result  = new Result();
        retroactive.setDate(new Date());
        retroactiveDao.insert(retroactive);
        result.setMsg("签到成功");
        result.setMsgcode("0");
        result.setSuccess(true);
        return  result;
    }

    /**
     * 签到
     */
    @ResponseBody
    @RequestMapping(value = "SignIn")
    @ApiOperation(value="签到", notes="签到")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "address", value = "签到地址", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "storeId", value = "门店id", required = true, dataType = "Long")
    })
    public Result SignIn(Long stuffId,String address,Long storeId,Double longitude,Double latitude){
        Result result=new Result();
        AttendanceSheet condition=new AttendanceSheet();
        condition.setStuffId(stuffId);
        condition.setAttendanceTime(new Date());
        condition.setAddress(address);
        Salon salon =  salonService.getSalonForId(storeId);
        String myAddress = longitude+","+latitude;
        String GAddress = salon.getLongitude()+","+salon.getLatitude();
        boolean flag = GaoDeUtil.getBooleanAddress(myAddress,GAddress);
        if(!flag){
            result.setMsg("签到失败,不在签到范围");
            result.setMsgcode("200");
            result.setSuccess(false);
            return result;
        }
        try {
            attendanceSheetService.insert(condition,storeId);
            result.setMsg("签到成功");
            result.setMsgcode("0");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("签到失败");
            result.setMsgcode("200");
            result.setSuccess(false);
        }
        System.out.println(JSONObject.toJSONString(result));
        return result;
    }



}
