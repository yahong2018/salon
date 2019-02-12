package com.zhxh.admin.controller;

import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.env.SysEnv;
import com.zhxh.core.utils.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class LoginController {
    private final static String LOGIN_URL="admin/login";

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @RequestMapping("/login")
    public String login() {
        return LOGIN_URL;
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
