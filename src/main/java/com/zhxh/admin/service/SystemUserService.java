package com.zhxh.admin.service;

import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemProgramDAO;
import com.zhxh.admin.dao.SystemRoleDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.*;
import com.zhxh.core.data.DataCode.BCode;
import com.zhxh.core.env.SysEnv;
import com.zhxh.core.utils.StringUtilsExt;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.zhxh.admin.misc.ErrorCode.*;
import static com.zhxh.core.data.DataCode.BCode.PRIVILEGE_RUN;
import static com.zhxh.core.exception.ErrorCode.*;
import static com.zhxh.core.exception.ExceptionHelper.throwException;

@Component("systemUserService")
public class SystemUserService {
    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;
    @Resource(name = "systemRoleDAO")
    private SystemRoleDAO systemRoleDAO;
    @Resource(name="systemRoleService")
    private SystemRoleService systemRoleService;

    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDAO;    
    @Resource(name="systemProgramDAO")
    private SystemProgramDAO systemProgramDAO;
    @Resource(name="authenticateService")
    private AuthenticateService authenticateService;

    public int changePassword(String pwd, String oldPwd) {
        SystemUser currentUser = authenticateService.getCurrentLogin();
        return this.changePassword(currentUser, pwd, oldPwd);
    }

    public int changePassword(SystemUser user, String pwd, String oldPwd){
        String md5 = StringUtilsExt.getMd5(oldPwd);
        if (!md5.equals(user.getPassword())) {
            throwException(ERROR_OLD_PASSWORD_ERROR);
        }
        user.setPassword(pwd);
        return systemUserDAO.update(user);
    }

    public int changePassword(String userId, String pwd, String oldPwd){
        SystemUser user = systemUserDAO.getById(userId);
        return this.changePassword(user, pwd, oldPwd);
    }

    @Transactional(rollbackFor = Exception.class)
    public int openLoginAccount(SystemUser item){
        //1.新建本身的数据
        //2.授予权限:这一步在权限授予里面完成
        //3.通知用户
        String passwordMd5 = StringUtilsExt.getMd5(item.getPassword());
        item.setPassword(passwordMd5);
        int result = systemUserDAO.insert(item);
        if (result == 1) {
            //TODO:在这里Email通知相关账户
        }
        return result;
    }
    
    public int update(SystemUser item) {
        SystemUser dbItem = systemUserDAO.getById(item.getRecordId());
        item.setPassword(dbItem.getPassword());
        return systemUserDAO.update(item);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delete(Long[] userIdList){
        //注意：正常来说，系统不允许删除账号
        //1.删除相关的权限信息
        //2.删除本身
        //3.通知系统管理员
    	for(Long userId:userIdList) {
	        roleUserDAO.revokeUserAllRoles(userId);
	        int result = systemUserDAO.deleteById(userId);
	        if(result!=1){
	            throwException(ERROR_UNKNOWN_EXCEPTION);
	        }
    	}
        //
        //TODO:在这里发送通知给管理员
        //
        return userIdList.length;
    }

    public int addRole(Long userId, Long roleId) {
        systemUserDAO.verifyExistsById(userId);
        systemRoleDAO.verifyExistsById(roleId);

        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(roleId);
        roleUser.setUserId(userId);

        return roleUserDAO.insert(roleUser);
    }

    public int removeRole(Long userId, Long roleId){
        RoleUser roleUser = roleUserDAO.getByUserIdAndRoleId(userId, roleId);
        if (roleUser != null) {
            roleUserDAO.delete(roleUser);
        }
        return 0;
    }

    public int disableUser(Long userId){
        SystemUser dbUser =  this.systemUserDAO.verifyExistsById(userId);
        dbUser.setUserStatus(BCode.STATUS_DISABLED);
        return systemUserDAO.update(dbUser);
    }

    public int enableUser(Long userId){
        SystemUser dbUser =  this.systemUserDAO.verifyExistsById(userId);
        dbUser.setUserStatus(BCode.STATUS_ENABLED);
        return systemUserDAO.update(dbUser);
    }

    public List<SystemRole> getUserRoles(Long userId) {
        return roleUserDAO.getUserRoles(userId);
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public int resetPassword(Long userId) {
        SystemUser dbUser =  this.systemUserDAO.verifyExistsById(userId);
        dbUser.setPassword(StringUtilsExt.getMd5(SystemUser.DEFAULT_PASSWORD));
        int result = systemUserDAO.update(dbUser);
        if (result != 1) {
            throwException(ERROR_UNKNOWN_EXCEPTION);
        }
        //
        // TODO:发送Email通知该用户
        //
        return result;
    }

    public SystemUserDAO getSystemUserDAO() {
        return this.systemUserDAO;
    }
    
//    public boolean hasPrivilege(String userId,String programId,String privilegeId) {
//    	List<SystemRole> roleList= this.getUserRoles(userId);
//    	for(SystemRole role : roleList) {
//			if(systemRoleService.hasPrivilege(role.getId(),programId,privilegeId)) {
//				return true;
//			}
//		}
//    	return false;
//    }

    public List<RolePrivilege> getUserAllPrivileges(Long userId){
        List<SystemRole> roles = this.getUserRoles(userId);
        List<RolePrivilege> result = new ArrayList<>();
        for(SystemRole role:roles){
            List<RolePrivilege> privileges = systemRoleService.getRolePrivileges(role.getRecordId());
            privileges.removeIf(x->result.stream().anyMatch(y->y.getPrivilegeCode()==x.getPrivilegeCode() && y.getProgramId().equals(x.getProgramId())));
            result.addAll(privileges);
        }

        return result;
    }
    
	public boolean canRun(Long userId, String url) {
	    List<SystemRole> roleList= this.getUserRoles(userId);
	    String pureUrl = url.replace(SysEnv.getAppRoot(),"");
		/**
		 * 1.首页：所有人都有权限:  "/mes";"/"
		 * 2.登录页：所有人都有权限:"/login"
         * 3.*.handler页：都有权限，因为是系统调用，所以必须要给权限。
		 * 4.其他页：要根据权限判断
		 */
		if(SysEnv.getAppRoot().equals(pureUrl) || SysEnv.getUrlAppIndex().equals(pureUrl)
                || SysEnv.getAppAbsoluteRootPath().equals(pureUrl) || SysEnv.getUrlLoginPage().equals(pureUrl)) {
			return true;
		}
		if(url.endsWith(".handler")){
		    return true;
        }

		SystemProgram program = systemProgramDAO.getSystemProgramByUrl(pureUrl);
		if(program==null) {
			return false;
		}
		for(SystemRole role : roleList) {
			if(systemRoleService.hasPrivilege(role.getRecordId(),program.getRecordId(), PRIVILEGE_RUN)) {
				return true;
			}
		}
		return false;
	}
}
