package com.hy.salon.basic.controller;

import com.github.pagehelper.PageHelper;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.JobDAO;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.MemberTag;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.util.DateString;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.entity.RoleUser;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDAO;

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

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
    public Result addJob(Job job){

        Result r= new Result();
            String jobName = job.getJobName();
            int jobLevel = job.getJobLevel();
            String where = "job_name = #{jobName} and job_level = #{jobLevel}";
            Map parameters = new HashMap();
            parameters.put("jobName", jobName);
            parameters.put("jobLevel", jobLevel);
            Job jobData = jobDAO.getOne(where, parameters);

            try {
                if (jobData == null) {
                   jobDAO.insert(job);
                    r.setMsg("请求成功");
                    r.setMsgcode(StatusUtil.OK);
                    r.setSuccess(true);
              }
         }catch (Exception e){
                e.printStackTrace();
                r.setMsg("请求失败");
                r.setMsgcode(StatusUtil.ERROR);
                r.setSuccess(false);
         }
   return  r;
    }

    @ApiOperation(value = "删除职务",notes = "删除职务")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="delete", name = "recordId", value = "职位id", required = true, dataType = "long"),
    })
    @RequestMapping(value = "/deleteJob",method = RequestMethod.POST)
    public Result deleteJob(@RequestBody String[] ids){

        Result result = new Result();
        try {
            if(StringUtils.isNotEmpty(ids+"")) {
                for (String id : ids) {
                    jobDAO.deleteById(id);
                }
            }
            result.setMsgcode(StatusUtil.OK);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMsgcode(StatusUtil.ERROR);
            result.setSuccess(false);
        }
        return result;


        /*if(StringUtils.isNotEmpty(recordId+"")){
            jobDAO.deleteById(recordId);
            return  "Success";
        }
        return  "";*/

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

    /**
     * 职务管理
     * @param page
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getJobs",method = RequestMethod.GET)
    public Result getJobs(Integer page,String limit,Long recordId){
        Result r= new Result();
        try {
            if (null == recordId) {
                SystemUser user = authenticateService.getCurrentLogin();
                Stuff stuff = stuffDao.getStuffForUser(user.getRecordId());
                recordId = stuff.getStoreId();
            }

            r.setTotal(jobDAO.getSeriesForUser().size());
            if (null == limit) {
                PageHelper.startPage(page, 10);
            } else {
                PageHelper.startPage(page, Integer.parseInt(limit));
            }
            List<Job> jobList = jobDAO.getSeriesForUser();

            r.setData(jobList);
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            r.setMsgcode(StatusUtil.ERROR);
            r.setSuccess(false);
        }
        return r;
    }


    @ResponseBody
    @RequestMapping("updateJob")
    @ApiOperation(value="修改职务", notes="修改职务")
    public Result updateJob( Job job) {
        Result r= new Result();
        try {
            jobDAO.update(job);
            r.setMsg("修改成功");
            r.setMsgcode(StatusUtil.OK);
            r.setSuccess(true);

        }catch (Exception e){
            e.printStackTrace();
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
        }

        return r;
    }

    /*public Result getJobs(Integer page,Integer limit){
        Result result=new Result();
        try {
            List<Job> list = jobDAO.getAll();
            result.setData(list);
            result.setMsgcode("0");
            result.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsgcode("200");
        }
        return  result;
    }*/

}
