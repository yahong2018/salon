package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.service.ScheduleService;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.ShiftVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/schedule")
public class ScheduleController {
    @Resource(name = "scheduleService")
    private ScheduleService scheduleService;
    /**
     * 按月获取所有员工当月的排班信息
     * recordId ,门店id
     */
    @ResponseBody
    @RequestMapping(value = "getScheduleByTime",method = RequestMethod.GET)
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

}
