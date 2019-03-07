package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.JobDAO;
import com.hy.salon.basic.entity.Job;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/job")
@Api(value = "JobController|员工职位控制层")
public class JobController extends SimpleCRUDController<Job> {

    @Resource(name="jobDAO")
    private JobDAO jobDAO;

    @Override
    protected BaseDAOWithEntity<Job> getCrudDao() {
        return jobDAO;
    }

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;


    @ApiOperation(value="添加职务", notes="添加职务")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="add", name = "jobName", value = "职位名称", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="add", name = "jobLevel", value = "职位等级", required = true, dataType = "int"),
    })
    @RequestMapping(value = "/addJob",method = RequestMethod.POST)
    public String addJob(Job job){
        String jobName = job.getJobName();
        String where="job_name=#{jobName}";
        Map parameters = new HashMap();
        parameters.put("jobName", jobName);
        Job jobData = jobDAO.getOne(where,parameters);
        String result = "";
        if(jobData!=null){
            jobDAO.insert(job);
            result = "添加成功";
            return  result;
        }else{
            result = "职务名称已经存在！";
            return result ;
        }

    }

    @ApiOperation(value = "删除职务",notes = "删除职务")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="delete", name = "recordId", value = "职位id", required = true, dataType = "long"),
    })
    @RequestMapping(value = "/deleteJob",method = RequestMethod.POST)
    public String deleteJob(long recordId){
        if(StringUtils.isNotEmpty(recordId+"")){
            jobDAO.deleteById(recordId);
            return  "Success";
        }
        return  "";

    }

    @ApiOperation(value = "查询职务",notes = "查询职务")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "recordId", value = "员工ID", required = false, dataType = "long"),
    })
    @RequestMapping(value = "queryJobs",method = RequestMethod.GET)
    public List<Job> queryJobs(long recordId){
        List<Job> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(recordId+"")){
            String where="record_id=#{recordId}";
            Map parameters = new HashMap();
            parameters.put("recordId", recordId);
            list = jobDAO.getByWhere(where,parameters);
        }else{
            list =  jobDAO.getAll();
        }
        return  list;
    }


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
