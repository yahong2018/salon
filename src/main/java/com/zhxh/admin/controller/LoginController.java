package com.zhxh.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.common.handler.Authorized;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.StuffJob;
import com.hy.salon.basic.service.JobService;
import com.hy.salon.basic.service.StuffJobService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.misc.LoginResult;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.admin.service.RoleUserService;
import com.zhxh.admin.service.SystemRoleService;
import com.zhxh.core.env.SysEnv;
import com.zhxh.core.utils.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Api(value = "LoginController|登陆")
public class LoginController {
    //private final static String LOGIN_URL="admin/login";
    private final static String LOGIN_URL="myLogin";
    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name="roleUserService")
    private RoleUserService roleUserService;

    @Resource(name="systemRoleService")
    private SystemRoleService systemRoleService;

    @Resource(name="jobService")
    private JobService jobService;

    @Resource(name = "salonDao")
    private SalonDao salonDao;


    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name = "stuffJobService")
    private StuffJobService stuffJobService;
    @RequestMapping("/login")
    public String login() {
        return LOGIN_URL;
    }

    @RequestMapping(value = "/api/login/doLogin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value="登陆返回用户角色，职务", notes="登陆返回用户角色，职务")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "userCode", value = "用户账户", required = true, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "password", value = "密码", required = true, dataType = "String"),
    })
    public LoginResult doLoginByApi(@RequestBody SystemUser user) {
        LoginResult result = new LoginResult();
        try {
            String userCode = user.getUserCode();
            String password = user.getPassword();
            authenticateService.authenticate(userCode, password);
            SystemUser systemUser = authenticateService.getUserByCode(userCode);
            long id = systemUser.getRecordId();
            Stuff stuff=stuffDao.getStuffForUser(id);

            List<SystemRole> list = systemRoleService.getRoleListById(id);
            List<Job> listJob = jobService.getJobList(stuff.getRecordId());


            List<Salon> salonList=salonDao.getSalonForStoreId2(stuff.getStoreId());


            Salon salon=salonDao.getSalonForId(stuff.getStoreId());
            if(salon.getAudit() == 0){
                result.setCode(LoginResult.LOGIN_CODE_ERROR);
                result.setMessage("门店还未通过审核！");
                return result;
            }

            result.setStuff(stuff);
            result.setListSalon(salonList);
            result.setSalonId(stuff.getStoreId());
            result.setListRole(list);
            result.setListJob(listJob);
            result.setCode(LoginResult.LOGIN_CODE_OK);
            result.setMessage("登录成功！");

        } catch (Exception e) {
            Logger.error(e);
            result.setCode(LoginResult.LOGIN_CODE_ERROR);
            result.setMessage(e.getMessage());
        }
        return result;
        /*
        客户端的调用方式

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"userCode\":\"C00001\",\"password\":\"123456\"}");
        Request request = new Request.Builder()
          .url("http://localhost:8080/api/login/doLogin")
          .post(body)
          .addHeader("Content-Type", "application/json")
          .addHeader("cache-control", "no-cache")
          .addHeader("Postman-Token", "55fc7d79-03fc-4441-ae3f-dca62fd94d10")
          .build();

        Response response = client.newCall(request).execute();

        返回结果如下：
        {
            "data": {
                "code": 0,
                "message": "登录成功！"
            },
            "success": true
        }
       */
    }
   // @Authorized
    @RequestMapping(value = "/login/doLogin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("测试登录")
    public String doLogin(Model model, String userCode, String password) {
        try {

          boolean flag =   authenticateService.adminAuthenticate(userCode,password);
          if(flag){
              String url = SysEnv.getAppRoot() + SysEnv.getUrlAppIndex();
              if (StringUtils.isEmpty(url)) {
                  url = "/";
              }
              return "redirect:" + url;
          }else{
              return  "error";
          }

        } catch (Exception e) {
            String errorMessage = e.getMessage();
            model.addAttribute("errorMessage", errorMessage);
            Logger.info(errorMessage);
        }

        return LOGIN_URL;
    }

    @RequestMapping("/login/logout")
    public String logout(){
        try {
            authenticateService.kickOffUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return LOGIN_URL;
    }

    @ResponseBody
    @RequestMapping(value = "/api/login/logout", method = RequestMethod.GET)
    public Result apiLogout(){
        Result r= new Result();
        try {
            authenticateService.kickOffUser();
        } catch (Exception e) {
            r.setMsgcode(StatusUtil.ERROR);
            r.setSuccess(false);
            e.printStackTrace();
            return r;
        }
        r.setMsgcode(StatusUtil.OK);
        r.setSuccess(true);
        return r;
    }
}
