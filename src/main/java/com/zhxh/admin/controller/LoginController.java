package com.zhxh.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.common.handler.Authorized;
import com.hy.salon.basic.dao.MemberDao;
import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.service.JobService;
import com.hy.salon.basic.service.StuffJobService;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.misc.LoginResult;
import com.zhxh.admin.misc.SessionManager;
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
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
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

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDAO;

    @Resource(name = "memberDao")
    private MemberDao memberDao;

    @Resource(name = "stuffJobService")
    private StuffJobService stuffJobService;

    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;

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
            SystemUser systemUser = authenticateService.getUserByCode(userCode);
            if(null==systemUser){
                result.setAreMember(false);
                result.setMsg("该用户未注册！");
                result.setSuccess(false);
                result.setMsgcode(LoginResult.LOGIN_CODE_ERROR);
                result.setData(null);
                return result;
            }

            authenticateService.authenticate(userCode, password);


            if(1==systemUser.getUserStatus()){
                result.setAreMember(false);
                result.setMsg("该用户已被禁用！");
                result.setSuccess(false);
                result.setMsgcode(LoginResult.LOGIN_CODE_ERROR);
                result.setData(null);
                return result;

            }

            long id = systemUser.getRecordId();

            //判断账号属于员工还是属于顾客
            String where="user_id=#{userId}";
            Map parameters = new HashMap();
            parameters.put("userId", id);
            RoleUser roleUser =roleUserDAO.getOne(where,parameters);

            if(roleUser.getRoleId()==3){
            //顾客

                Member member=memberDao.getMemberForTel(user.getUserCode());
                result.setMember(member);
                result.setAreMember(true);
                result.setMsg("登录成功！");
                result.setSuccess(true);
                result.setMsgcode(LoginResult.LOGIN_CODE_OK);
                result.setData(null);
                return result;
            }else{
                Stuff stuff=stuffDao.getStuffForUser(id);
                List<SystemRole> roleList = systemRoleService.getRoleListById(id);
                List<Job> listJob = jobService.getJobList(stuff.getRecordId());

                List<Salon> salonList=salonDao.getSalonForStoreId2(stuff.getStoreId());


                Salon salon=salonDao.getSalonForId(stuff.getStoreId());

                if(salon.getAudit() == 0){
                    result.setMsgcode(LoginResult.LOGIN_CODE_ERROR);
                    result.setMsg("门店还未通过审核！");
                    return result;
                }
                if(null != salon){
                    result.setSalonName(salon.getSalonName());
                }

                result.setStuff(stuff);
                result.setListSalon(salonList);
                result.setSalonId(stuff.getStoreId());
                result.setListRole(roleList);
                result.setListJob(listJob);
                result.setMsgcode(LoginResult.LOGIN_CODE_OK);
                result.setMsg("登录成功！");
                result.setSuccess(true);
                result.setAreMember(false);
                result.setData(null);
            }



        } catch (Exception e) {
            Logger.error(e);
            result.setMsgcode(LoginResult.LOGIN_CODE_ERROR);
            result.setMsg(e.getMessage());
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
    public String doLogin(Model model, String userCode, String password
            ,String randomCode, HttpServletRequest req) {
        try {
            String code= (String) req.getSession().getAttribute("code");
            if(!code.equalsIgnoreCase(randomCode)){
                return  "验证码错误";
            }


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


    @RequestMapping(value = "/weChat/login/doLogin")
    @ResponseBody
    @ApiOperation(value="小程序登录", notes="小程序登录")
    public LoginResult doLoginByWeChat(String tel) {
        LoginResult result = new LoginResult();

        try {

            List<SystemUser>  systemUserList=systemUserDAO.getUserByRecordTel(tel);

            if(systemUserList.size()==0){
                result.setMsgcode(LoginResult.LOGIN_CODE_ERROR);
                result.setMsg("该顾客不存在");
                result.setSuccess(false);
                result.setData(null);
                return result;
            }


            //判断账号属于员工还是属于顾客
            String where = "user_id=#{userId}";
            Map parameters = new HashMap();
            parameters.put("userId", systemUserList.get(0).getRecordId());
            RoleUser roleUser = roleUserDAO.getOne(where, parameters);

            if (roleUser.getRoleId() == 3) {
                //顾客

                Member member = memberDao.getMemberForTel(systemUserList.get(0).getUserCode());

                //2.更新Session
                setCurrentLogin(systemUserList.get(0));
                //3.更新数据库
                systemUserList.get(0).setLastLoginTime(new Timestamp(System.currentTimeMillis()));
                systemUserList.get(0).setOnline(true);

                systemUserDAO.update(systemUserList.get(0));


                result.setMember(member);
                result.setAreMember(true);
                result.setMsg("登录成功！");
                result.setSuccess(true);
                result.setData(null);
            } else {
                result.setAreMember(false);
                result.setMsgcode(LoginResult.LOGIN_CODE_ERROR);
                result.setMsg("门店员工不允许登录");
                result.setSuccess(false);
                result.setData(null);
            }


        } catch (Exception e) {
            Logger.error(e);
            result.setMsgcode(LoginResult.LOGIN_CODE_ERROR);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    public synchronized void setCurrentLogin(SystemUser currentLogin) {
        SessionManager.getCurrentSession().setAttribute(CURRENT_LOGIN_STORED_ID, currentLogin);
    }
    protected static final String CURRENT_LOGIN_STORED_ID = "{9D929EBB-1006-4597-A5E0-F159BB93AA60}";
}
