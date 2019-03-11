package com.hy.salon.basic.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.entity.Shift;
import com.hy.salon.basic.service.NurseLogService;
import com.hy.salon.basic.service.ShiftService;
import com.hy.salon.basic.vo.NurseLogVo;
import com.hy.salon.basic.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/shift")
public class ShiftController {
    @Resource(name = "shiftService")
    private ShiftService shiftService;
    /**
     * 保存修改排班设置
     */
    @ResponseBody
    @RequestMapping(value = "saveShift",method = RequestMethod.POST)
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

}
