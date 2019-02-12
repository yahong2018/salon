package com.zhxh.admin.controller;

import com.zhxh.admin.entity.RolePrivilege;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.admin.service.MainPageService;
import com.zhxh.admin.service.SystemProgramService;
import com.zhxh.admin.service.SystemUserService;
import com.zhxh.admin.vo.SystemMenu;
import com.zhxh.admin.vo.SystemProgramWithChildren;
import com.zhxh.admin.vo.SystemUserWithPrivilege;
import com.zhxh.core.utils.BeanUtils;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import com.zhxh.core.web.ListRequestProcessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/mainPage")
public class MainPageController {
	private final ListRequestProcessHandler listRequestProcessHandler = new ListRequestProcessHandler();
    @Resource(name = "mainPageService")
    private MainPageService mainPageService;

    @Resource(name = "authenticateService")
    private AuthenticateService authenticateService;

    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;
    
    @Resource(name = "systemProgramService")
    private SystemProgramService systemProgramService;

    @RequestMapping("getUserMenu.handler")
    @ResponseBody
    public List<SystemMenu> getUserMenu() {
        return mainPageService.getCurrentUserMenu();
    }

    @RequestMapping("getCurrentLogin.handler")
    @ResponseBody
    public SystemUserWithPrivilege getCurrentUser() {
        SystemUser result = authenticateService.getCurrentLogin();
        if(result==null){
            return null;
        }
        result.setPassword("");
        List<RolePrivilege> privileges = systemUserService.getUserAllPrivileges(result.getRecordId());
        SystemUserWithPrivilege systemUserWithPrivilege = new SystemUserWithPrivilege();
        BeanUtils.copy(result,systemUserWithPrivilege);
        systemUserWithPrivilege.setPrivilegeList(privileges);

        return systemUserWithPrivilege;
    }
    
    @RequestMapping("getAllMenu.handler")
	@ResponseBody
	public List<SystemProgramWithChildren> getAll(){
        return  systemProgramService.getAllWithChildren();
    }

    @RequestMapping("getAllByPage.handler")
    @ResponseBody
    public ExtJsResult getAllByPage(HttpServletRequest request, HttpServletResponse response) {
        return listRequestProcessHandler.getListFromHttpRequest(request, new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return systemProgramService.getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return systemProgramService.getPageListCount(listRequest.toMap(), null);
            }
        });
    }
}
