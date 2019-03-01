package com.hy.salon.basic.controller;

import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.AttendanceSheet;
import com.hy.salon.basic.service.AttendanceSheetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/hy/basic/attendance")
public class AttendanceController extends BaseController{

    @Resource(name = "attendanceSheetService")
    private AttendanceSheetService attendanceSheetService;


    /**
     * 考勤签到例子
     */
    @RequestMapping("SignInDemo")
    public void SignInDemo(HttpServletResponse resp){
        AttendanceSheet condition=new AttendanceSheet();
        condition.setStuffId(new Long(1));
        condition.setAttendanceTime(new Date());
        condition.setAddress("常平店");
        System.out.println("签到");

        attendanceSheetService.insert(condition);
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("respCode","0000");

        write(jsonObj,resp);
    }





}
