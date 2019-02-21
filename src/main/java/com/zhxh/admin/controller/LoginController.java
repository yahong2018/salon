package com.zhxh.admin.controller;

import com.zhxh.admin.misc.LoginResult;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.env.SysEnv;
import com.zhxh.core.utils.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class LoginController {
    private final static String LOGIN_URL="admin/login";

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @RequestMapping("/login")
    public String login() {
        return LOGIN_URL;
    }

    @RequestMapping(value = "/api/login/doLogin", method = RequestMethod.POST)
    @ResponseBody
    public LoginResult doLoginByApi(@RequestBody Map user) {
        LoginResult result = new LoginResult();
        try {
            String userCode = user.get("userCode").toString();
            String password = user.get("password").toString();
            authenticateService.authenticate(userCode, password);
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

    @RequestMapping(value = "/login/doLogin", method = RequestMethod.POST)
    public String doLogin(Model model, String userCode, String password) {
        try {
            authenticateService.authenticate(userCode,password);
            String url = SysEnv.getAppRoot() + SysEnv.getUrlAppIndex();
            if (StringUtils.isEmpty(url)) {
                url = "/";
            }
            return "redirect:" + url;
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
}
