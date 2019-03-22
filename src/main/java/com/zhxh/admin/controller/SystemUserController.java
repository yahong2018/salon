package com.zhxh.admin.controller;

import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.RoleUserService;
import com.zhxh.admin.service.SystemUserService;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import com.zhxh.core.web.ListRequestProcessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin/systemUsers")
public class SystemUserController {
    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;

    @Resource(name = "roleUserService")
    private RoleUserService roleUserService;

    private final ListRequestProcessHandler listRequestProcessHandler = new ListRequestProcessHandler();

    @RequestMapping("getAllUsers.handler")
    @ResponseBody
    public ExtJsResult getAllUsers(HttpServletRequest request, HttpServletResponse response) {

        return listRequestProcessHandler.getListFromHttpRequest(request, new ListRequestBaseHandler() {
            @Override
            public List getByRequest(ListRequest listRequest) {
                return systemUserService.getSystemUserDAO().getPageList(listRequest.toMap(), null);
            }

            @Override
            public int getRequestListCount(ListRequest listRequest) {
                return systemUserService.getSystemUserDAO().getPageListCount(listRequest.toMap(), null);
            }
        });
    }

    @RequestMapping("openLoginAccount.handler")
    @ResponseBody
    public SystemUser openLoginAccount(SystemUser user) {
        systemUserService.openLoginAccount(user);
        return user;
    }

    @RequestMapping("update.handler")
    @ResponseBody
    public SystemUser update(SystemUser user) {
        systemUserService.update(user);
        return user;
    }

    @RequestMapping("delete.handler")
    @ResponseBody
    public int delete(@RequestBody Long[] userIdList) {
        return systemUserService.delete(userIdList);
    }

    @RequestMapping("disableUser.handler")
    @ResponseBody
    public int disableUser(Long userId) {
        return systemUserService.disableUser(userId);
    }

    @RequestMapping("enableUser.handler")
    @ResponseBody
    public int enableUser(Long userId) {
        return systemUserService.enableUser(userId);
    }

    @RequestMapping("resetPassword.handler")
    @ResponseBody
    public int resetPassword(Long userId) {
        return systemUserService.resetPassword(userId);
    }

    @RequestMapping("getUserRoles.handler")
    @ResponseBody
    public List<SystemRole> getUserRoles(Long userId) {
        return systemUserService.getUserRoles(userId);
    }

    @RequestMapping("updateUserRoles.handler")
    @ResponseBody
    public int updateRoles(Long userId, @RequestBody RoleUser[] roleUsers) {
        return roleUserService.updateUserRoles(userId, roleUsers);
    }

    @RequestMapping("addRole.handler")
    @ResponseBody
    public int addRole(Long userId, Long roleId) {
        return systemUserService.addRole(userId, roleId);
    }

    @RequestMapping("removeRole.handler")
    @ResponseBody
    public int removeRole(Long userId, Long roleId) {
        return systemUserService.removeRole(userId, roleId);
    }
}
