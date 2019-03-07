package com.hy.salon.basic.controller;

import com.hy.salon.basic.entity.AttendanceSheet;
import com.hy.salon.basic.service.AttendanceSheetService;
import com.hy.salon.basic.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/hy/basic/attendance")
public class AttendanceController {

    @Resource(name = "attendanceSheetService")
    private AttendanceSheetService attendanceSheetService;


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
     * 签到
     */
    @ResponseBody
    @RequestMapping(value = "SignIn",method = RequestMethod.GET)
    public Result SignIn(Long stuffId,String address,Long storeId){
        Result result=new Result();
        AttendanceSheet condition=new AttendanceSheet();
        condition.setStuffId(stuffId);
        condition.setAttendanceTime(new Date());
        condition.setAddress(address);
        try {
            attendanceSheetService.insert(condition,storeId);
            result.setMsg("签到成功");
            result.setMsgcode("0");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setMsg("签到失败");
            result.setMsgcode("1");
            result.setSuccess(false);
        }
        return result;
    }



}
