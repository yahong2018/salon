package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.service.NurseLogService;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.Result;
import com.hy.salon.basic.vo.TimeSheetVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/basic/nurseLog")
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
}
