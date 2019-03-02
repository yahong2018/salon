package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.JobDAO;
import com.hy.salon.basic.entity.Job;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/job")
public class JobController extends SimpleCRUDController<Job> {

    @Resource(name="jobDAO")
    private JobDAO jobDAO;

    @Override
    protected BaseDAOWithEntity<Job> getCrudDao() {
        return jobDAO;
    }

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;


    @RequestMapping("/testDemo")
    public void testDemo(HttpServletResponse resp){

        SystemUser user = authenticateService.getCurrentLogin();
        System.out.println("username="+user.getUserName());
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("respCode","0000");
        System.out.println("success");
        try {
            resp.setCharacterEncoding("UTF-8");
            PrintWriter pw = resp.getWriter();
            pw.write(jsonObj.toJSONString());
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
