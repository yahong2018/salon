package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONArray;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Shift;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.service.NurseLogService;
import com.hy.salon.basic.service.SalonService;
import com.hy.salon.basic.service.ShiftService;
import com.hy.salon.basic.vo.NurseLogVo;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/shift")

@Api(value = "ShiftController| 门店排班控制器")
public class ShiftController {
    @Resource(name = "shiftService")
    private ShiftService shiftService;
    @Resource(name="authenticateService")
    private AuthenticateService authenticateService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    @Resource(name = "salonService")
    private SalonService salonService;


    /**
     * 查询一个门店的排班
     */
    @RequestMapping("/getSalonShift")
    @ResponseBody
    public ExtJsResult getSalonShift(HttpServletRequest request){
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

        List<Shift>  list =shiftService.getSalonShift(stuff.getStoreId());
        ExtJsResult er  = new ExtJsResult();
        er.setSuccess(true);
        er.setMsgcode(StatusUtil.OK);
        er.setData(list);
        return  er;
    }

    /**
     * 查询门店排班
     */
    @RequestMapping("/getSalonShiftList")
    @ResponseBody
    public ExtJsResult getList(HttpServletRequest request,int page){
        SystemUser user = authenticateService.getCurrentLogin();
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());

        ExtJsResult StoreList=salonService.getSalonForStoreIdSystem(request,stuff.getStoreId(),new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return salonService.getSalonDao().getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return salonService.getSalonDao().getPageListCount(listRequest.toMap(), null);
            }
        });

        return  StoreList;
    }


    /**
     * 保存修改排班设置
     */
    @ResponseBody
    @RequestMapping(value = "saveShift",method = RequestMethod.POST)
    @ApiOperation(value="保存或修改門店排班设置", notes="保存或修改門店排班设置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "list", value = "排班信息json数据  {\"list\":[{\"storeId\": \"xxx\",\"shiftType\": \"xxx\",\"timeStart\": \"xxx\",\"timeEnd\": \"xxx\"}]}", required = true, dataType = "List<Shift>")
    })
    public Result saveShift(@RequestBody List<Shift> list){
        Result result=new Result();
        try {
            shiftService.saveShift(list);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }

    /**
     * 保存修改排班设置
     */
    @ResponseBody
    @RequestMapping(value = "saveShiftAPP")
    @ApiOperation(value="保存或修改門店排班设置", notes="保存或修改門店排班设置")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "list", value = "排班信息json数据  {\"list\":[{\"storeId\": \"xxx\",\"shiftType\": \"xxx\",\"timeStart\": \"xxx\",\"timeEnd\": \"xxx\"}]}", required = true, dataType = "List<Shift>")
    })
    public Result saveShiftAPP(HttpServletRequest request){
        String list  = request.getParameter("list");
        List<Shift> listS =   JSONArray.parseArray(list,Shift.class);
        Result result=new Result();
        try {
            shiftService.saveShift(listS);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }

}
