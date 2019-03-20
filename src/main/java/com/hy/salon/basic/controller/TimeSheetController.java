package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.TimeSheetDao;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.TimeSheet;
import com.hy.salon.basic.service.StuffService;
import com.hy.salon.basic.service.TimeSheetService;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.TimeSheetVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/timeSheet")
@Api(value = "TimeSheetController| 考勤控制器")
public class TimeSheetController extends SimpleCRUDController<TimeSheet> {
    @Resource(name = "timeSheetDao")
    private TimeSheetDao timeSheetDao;
    @Resource(name = "timeSheetService")
    private TimeSheetService timeSheetService;
    @Override
    protected BaseDAOWithEntity<TimeSheet> getCrudDao() {
        return timeSheetDao;
    }

    /**
     * 获取当天每家门店考勤情况
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTimeSheet",method = RequestMethod.GET)
    @ApiOperation(value="获取当天每家门店考勤", notes="获取当天每家门店考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "cTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "eTime", value = "结束时间", required = true, dataType = "String")
    })
    public Result getTimeSheet(String cTime, String eTime){
        Result result=new Result();
        try {
            List<TimeSheetVo> timeSheet = timeSheetService.getTimeSheet(cTime, eTime);
            result.setData(timeSheet);
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
     * 考勤2
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getTimeSheetBySalonId",method = RequestMethod.GET)
    @ApiOperation(value="获取当天門店考勤", notes="获取当天門店考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "salonId", value = "門店id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "time", value = "当天日期", required = true, dataType = "String")
    })
    public Result getTimeSheetBySalonId(String salonId,String time){
        Result result=new Result();
        try {
            timeSheetService.getTimeSheetBySalonId(salonId,time);
            //result.setData(list);
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
