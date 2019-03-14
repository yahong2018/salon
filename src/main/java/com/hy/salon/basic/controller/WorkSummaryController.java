package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.service.WorkSummaryService;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.SalonVo;
import com.hy.salon.basic.vo.StuffVo;
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
@RequestMapping("/hy/basic/workSummary")
@Api(value = "WorkSummaryController| 员工总结控制器")
public class WorkSummaryController {
    @Resource(name="workSummaryService")
    private WorkSummaryService workSummaryService;
    /**
     * 按门店查询当天员工已写总结数
     */
    @ResponseBody
    @RequestMapping(value = "getWorkSummaryBySalonId",method = RequestMethod.GET)
    @ApiOperation(value="按门店查询当天员工已写总结数", notes="按门店查询当天员工已写总结数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "门店id", required = true, dataType = "Long")
    })
    public Result getWorkSummaryBySalonId(Long recordId){
        Result result=new Result();
        try {
            List<StuffVo> list = workSummaryService.getWorkSummaryBySalonId(recordId);
            result.setData(list);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
    /**
     *查询当月各门店员工已写工作总结数
     */
    @ResponseBody
    @RequestMapping(value = "getWorkSummary",method = RequestMethod.GET)
    @ApiOperation(value="查询当月各门店员工已写工作总结数", notes="查询当月各门店员工已写工作总结数")
    public Result getWorkSummary(){
        Result result=new Result();
        try {
            List<SalonVo> list = workSummaryService.getWorkSummary();
            result.setData(list);
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;
    }
}
