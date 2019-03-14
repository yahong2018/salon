package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.entity.NurseLogModel;
import com.hy.salon.basic.service.NurseLogService;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.TimeSheetVo;
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
@RequestMapping("/hy/basic/nurseLog")
@Api(value = "NurseLogController| 护理控制器")
public class NurseLogController {

    @Resource(name = "nurseLogService")
    private NurseLogService nurseLogService;

    /**
     * 按门店查询护理日志
     * @param logType 日志类型 0 回访日志 1 护理日志
     * @param timeStart
     * @param timeEnd
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getNurseLogBySalon",method = RequestMethod.GET)
    @ApiOperation(value="按门店查询护理日志", notes="按门店查询护理日志")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "logType", value = "logType 日志类型 0 回访日志 1 护理日志", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "timeStart", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "timeEnd", value = "结束时间", required = true, dataType = "String")
    })
    public Result getNurseLogBySalon(Integer logType,String timeStart, String timeEnd){
        Result result=new Result();
        try {
            List<NurseLogVo> list = nurseLogService.getNurseLogBySalon(logType, timeStart, timeEnd);
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
     * 查询日志模板
     */
    @ResponseBody
    @RequestMapping(value = "getLogModel",method = RequestMethod.GET)
    @ApiOperation(value="查询日志模板", notes="查询日志模板")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageNo", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType="query", name = "pageSize", value = "页面大小", required = true, dataType = "Integer")

    })
    public Result getLogModel(Integer pageNo,Integer pageSize){
        if(pageNo==null){
            pageNo=1;
        }
        Result result=new Result();
        try {
            List<NurseLogModel> list = nurseLogService.getLogModel(pageNo, pageSize);
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
}
