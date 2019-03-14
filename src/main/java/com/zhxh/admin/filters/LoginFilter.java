package com.zhxh.admin.filters;

import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.admin.service.SystemUserService;
import com.zhxh.core.env.SysEnv;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Resource(name="systemUserService")
    private SystemUserService systemUserService;

    @Resource(name="authenticateService")
    private AuthenticateService authenticateService;

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;

                String redirectUrl = "";
                String url = httpServletRequest.getRequestURI();
                if(!"/".equalsIgnoreCase(url)&& !url.endsWith(".handler")){
                    chain.doFilter(request, response);
                    return;
                }

                if (!url.contains(SysEnv.getUrlLoginPage())) {
                    SystemUser currentLogin = authenticateService.getCurrentLogin();
                    if(currentLogin==null /* 没有登录 */
                            || !systemUserService.canRun(currentLogin.getRecordId(), url)/* 当前用户没有权限 */) {
                        redirectUrl = SysEnv.getAppRoot() + SysEnv.getUrlLoginPage();
                    }
                }
                if (!redirectUrl.isEmpty()) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.sendRedirect(redirectUrl);
                    return;
                }
            }
            chain.doFilter(request, response);
        } finally {
        }
    }

    @Override
    public void destroy() {
    }

}
