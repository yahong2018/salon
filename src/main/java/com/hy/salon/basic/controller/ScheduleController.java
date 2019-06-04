package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSON;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.common.handler.CommonExceptionAdvice;
import com.hy.salon.basic.common.handler.CommonUtil;
import com.hy.salon.basic.dao.ScheduleDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Schedule;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.service.ScheduleService;
import com.hy.salon.basic.util.DateString;
import com.hy.salon.basic.util.TimeBeginAndEndOFaMonth;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.ShiftVo;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/hy/basic/schedule")
@Api(value = "ScheduleController| 員工排班控制器")
public class ScheduleController  {
    @Resource(name = "scheduleService")
    private ScheduleService scheduleService;
    @Resource(name = "stuffDao")
    private StuffDao stuffDao;
    @Resource(name = "scheduleDao")
    private ScheduleDao scheduleDao;
    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;
    private static  Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    /**
     * 获取一个店有排班信息的所有员工
     * recordId ,门店id
     */
    @RequestMapping(value = "getDayStuffScheduleByTime",method = RequestMethod.GET)
    @ApiOperation(value="获取一个店有排班信息的所有员工", notes="获取一个店有排班信息的所有员工")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "storeId", value = "门店id", required = true, dataType = "Long"),
            @ApiImplicitParam(paramType="query", name = "time", value = "某年某月某日", required = true, dataType = "String"),
    })
    @ResponseBody
    public ExtJsResult getDayStuffScheduleByTime(HttpServletRequest request, String timeString ,Long storeId){
        ExtJsResult stuffList = new  ExtJsResult();
        Map rMapS = new HashMap();
        String whereS = " store_id=#{storeId} ";
        rMapS.put("storeId",storeId);
        List<Stuff> listStuff = stuffDao.getByWhere(whereS,rMapS);
        List<Stuff> list = new ArrayList<>();
        if(listStuff.size()>0){
            for(Stuff stuff:listStuff){
                Map rMap = new HashMap();
                String where = "stuff_id=#{stuffId} and  day = #{day}";
                rMap.put("stuffId",stuff.getRecordId());
                rMap.put("day",timeString);
                Schedule schedule =  scheduleDao.getOne(where,rMap);
                if(schedule!=null){
                    list.add(stuff);
                }
            }
        }
        stuffList.setMsgcode("0");
        stuffList.setData(list);
        stuffList.setMsg("可以预约员工");
        return  stuffList;
    }


    /**
     * 后台管理系统按月获取=============一个员工当月的排班信息
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
     * 后台管理系统按月获取==============所有员工当月的排班信息
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

        if(recordId==null){
            SystemUser user = authenticateService.getCurrentLogin();
            Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
            recordId = stuff.getStoreId();
        }


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
    /**
     * 修改或保存员工的排班信息APP
     */
    @ResponseBody
    @RequestMapping(value = "updateStuffScheduleApp")
    @ApiOperation(value="修改或保存员工的排班信息", notes="修改或保存员工的排班信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "list", value = "排班信息json数据", required = true, dataType = "List<Schedule>")
    })
    public Result updateStuffScheduleApp(HttpServletRequest request){
       String listString =  request.getParameter("list");
        List<Schedule> list = JSON.parseArray(listString, Schedule.class);
        Result result=new Result();
        try {
            scheduleService.updateStuffSchedule(list);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            logger.error("通用异常", e);
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }
        return result;
    }



    
    @ResponseBody
    @RequestMapping(value = "updateStuffScheduleApp2",method = RequestMethod.GET)
    public Result updateStuffScheduleApp2(HttpServletRequest request){
        Result result=new Result();
        String listString =  request.getParameter("list");


       /* try {
            List<ShiftVo> list = scheduleService.getScheduleByTime(recordId, timeStart, timeEnd);
            result.setData(list);
            result.setSuccess(true);
            result.setMsgcode(StatusUtil.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode(StatusUtil.ERROR);
        }*/
        return result;
    }
/*    public static void main(String[] args) {

        System.out.println("Hello World!");

        logger.info("logger info...");

        logger.debug("logger debug...");

        logger.error("logger error...");
    }*/

}
