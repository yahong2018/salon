package com.zhxh.admin.controller;

import com.hy.salon.basic.common.StatusUtil;
import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.dao.StuffJobDao;
import com.hy.salon.basic.dao.VerificationCodeTemporaryDAO;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.VerificationCodeTemporary;
import com.hy.salon.basic.vo.Result;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.misc.LoginResult;
import com.zhxh.admin.service.RoleUserService;
import com.zhxh.admin.service.SystemUserService;
import com.zhxh.core.utils.StringUtilsExt;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/systemUsers")
public class SystemUserController {
    @Resource(name = "systemUserService")
    private SystemUserService systemUserService;

    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDao;

    @Resource(name = "roleUserService")
    private RoleUserService roleUserService;


    @Resource(name = "stuffDao")
    private StuffDao stuffDao;

    @Resource(name= "verificationCodeTemporaryDao")
    private VerificationCodeTemporaryDAO verificationCodeTemporaryDAO;

    private final ListRequestProcessHandler listRequestProcessHandler = new ListRequestProcessHandler();

    @RequestMapping("getAllUsers.handler")
    @ResponseBody
    public ExtJsResult getAllUsers(HttpServletRequest request, HttpServletResponse response) {
        String filterExpr   = request.getParameter("filterExpr");
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
    @RequestMapping("updatePassword")
    @ResponseBody
    public Result updatePassword(String password,String tel,String verificationCode){
        Result r= new Result();
        SystemUser user = systemUserService.getUserByTel(tel);
        Stuff stuff=stuffDao.getStuffForUser(user.getRecordId());
        if(1==stuff.getIsDelete()){
            r.setMsg("该用户已被删除");
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            return r;

        }

        if(1==stuff.getInOffice()){
            r.setMsg("该用户已被离职");
            r.setSuccess(false);
            r.setMsgcode(StatusUtil.ERROR);
            return r;

        }

        user.setPassword(password);
        List<VerificationCodeTemporary> verificationCodeTemporaryList=verificationCodeTemporaryDAO.getCode(tel);
        if(verificationCodeTemporaryList.size()!=0){
            VerificationCodeTemporary verificationCodeTemporary=verificationCodeTemporaryList.get(verificationCodeTemporaryList.size()-1);
            if(!verificationCodeTemporary.getVerificationCode().equals(verificationCode)){
                r.setMsg("验证码错误");
                r.setSuccess(false);
                r.setMsgcode(StatusUtil.ERROR);
                return r;
            }else{
                systemUserService.updateApp(user);
                r.setMsg("验证成功");
                r.setSuccess(true);
                r.setMsgcode(StatusUtil.OK);
                return r;
            }

        }

        return  r;
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

    @RequestMapping("updatePwd")
    @ResponseBody
    public Result updatePwd(SystemUser condition) {
        Result r = new Result();
        String passwordMd5 = StringUtilsExt.getMd5(condition.getPassword());
        condition.setPassword(passwordMd5);
        systemUserDao.update(condition);
        r.setMsg("请求成功");
        r.setMsgcode("0");
        r.setSuccess(true);
        return r;
    }

}
