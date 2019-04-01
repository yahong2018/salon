package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Schedule;
import com.hy.salon.basic.service.ScheduleService;
import com.hy.salon.basic.util.DateString;
import com.hy.salon.basic.util.TimeBeginAndEndOFaMonth;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.ShiftVo;
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

@Controller
@RequestMapping("/hy/basic/schedule")
@Api(value = "ScheduleController| 員工排班控制器")
public class ScheduleController  {
    @Resource(name = "scheduleService")
    private ScheduleService scheduleService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;


    /**
     * 后台管理系统按月获取一个员工当月的排班信息
     * recordId ,门店id
     */
    @RequestMapping(value = "getAdminStuffScheduleByTime",method = RequestMethod.GET)
    @ApiOperation(value="按月获取一个员工当月的排班信息", notes="按月获取一个员工当月的排班信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "stuffId", value = "员工ID", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "某年某月", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "开始时间", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "结束时间", required = false, dataType = "String")
    })
    @ResponseBody
    public ExtJsResult getAdminStuffScheduleByTime(HttpServletRequest request,Long recordId, String time, String timeStart, String timeEnd,String stuffId){
        Date timeStartDate = null;
        Date timeEndDate = null;
        Date[] dataList = null;
        if(StringUtils.isNotEmpty(time)){
            String[] temp = time.split("-");
            dataList = TimeBeginAndEndOFaMonth.getDates(temp[0],temp[1]);
            timeStartDate =  TimeBeginAndEndOFaMonth.getBeginTime(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
            timeEndDate =  TimeBeginAndEndOFaMonth.getEndTime(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
        }else{
            timeStartDate = DateString.StringToDate(timeStart);
            timeEndDate = DateString.StringToDate(timeEnd);
        }

        ExtJsResult StoreList =  scheduleService.getAdminStuffScheduleByTime(dataList,timeStartDate,timeEndDate,recordId,stuffId);
        return  StoreList;
    }



    /**
     * 后台管理系统按月获取所有员工当月的排班信息
     * recordId ,门店id
     */
    @RequestMapping(value = "getAdminScheduleByTime",method = RequestMethod.GET)
    @ApiOperation(value="按月获取所有员工当月的排班信息", notes="按月获取所有员工当月的排班信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "某年某月", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "开始时间", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "结束时间", required = false, dataType = "String")
    })
    @ResponseBody
    public ExtJsResult getAdminScheduleByTime(HttpServletRequest request,Long recordId, String time, String timeStart, String timeEnd){
        Date timeStartDate = null;
        Date timeEndDate = null;
        Date[] dataList = null;
        if(StringUtils.isNotEmpty(time)){
            String[] temp = time.split("-");
             dataList = TimeBeginAndEndOFaMonth.getDates(temp[0],temp[1]);
             timeStartDate =  TimeBeginAndEndOFaMonth.getBeginTime(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
             timeEndDate =  TimeBeginAndEndOFaMonth.getEndTime(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
        }else{
            timeStartDate = DateString.StringToDate(timeStart);
            timeEndDate = DateString.StringToDate(timeEnd);
        }

        ExtJsResult StoreList=scheduleService.getScheduleForStoreIdSystem(dataList,timeStartDate,timeEndDate,request,recordId,new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return stuffDao.getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return stuffDao.getPageListCount(listRequest.toMap(), null);
            }
        });
        StoreList.setSuccess(true);
        StoreList.setMsgcode(StatusUtil.OK);
        return  StoreList;
    }
    /**
     * 按月获取所有员工当月的排班信息
     * recordId ,门店id
     */
    @ResponseBody
    @RequestMapping(value = "getScheduleByTime",method = RequestMethod.GET)
    @ApiOperation(value="按月获取所有员工当月的排班信息", notes="按月获取所有员工当月的排班信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "结束时间", required = true, dataType = "String")
    })
    public Result getScheduleByTime(Long recordId,String timeStart,String timeEnd){
        Result result=new Result();
        try {
            List<ShiftVo> list = scheduleService.getScheduleByTime(recordId, timeStart, timeEnd);
            result.setData(list);
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
     * 修改或保存员工的排班信息
     */
    @ResponseBody
    @RequestMapping(value = "updateStuffSchedule",method = RequestMethod.POST)
    @ApiOperation(value="修改或保存员工的排班信息", notes="修改或保存员工的排班信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "list", value = "排班信息json数据", required = true, dataType = "List<Schedule>")
    })
    public Result updateStuffSchedule(@RequestBody List<Schedule> list){
        Result result=new Result();
        try {
            scheduleService.updateStuffSchedule(list);
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
