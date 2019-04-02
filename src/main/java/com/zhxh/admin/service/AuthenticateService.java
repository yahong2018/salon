package com.zhxh.admin.service;

import com.zhxh.admin.dao.RoleUserDAO;
import com.zhxh.admin.dao.SystemRoleDAO;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.admin.entity.RoleUser;
import com.zhxh.admin.entity.SystemRole;
import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.misc.SessionManager;
import com.zhxh.core.utils.StringUtilsExt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

import static com.zhxh.admin.misc.ErrorCode.*;
import static com.zhxh.core.exception.ExceptionHelper.throwException;

@Component("authenticateService")
public class AuthenticateService {
    @Resource(name = "systemUserDAO")
    private SystemUserDAO systemUserDAO;
    @Resource(name = "roleUserDAO")
    private RoleUserDAO roleUserDAO;
    @Resource(name = "systemRoleDAO")
    private SystemRoleDAO systemRoleDAO;
    public SystemUser getSessionLogin(HttpSession session) {
        return (SystemUser) session.getAttribute(CURRENT_LOGIN_STORED_ID);
    }

    public static SystemUser getCurrentLogin() {
        if (SessionManager.getCurrentSession() == null) {
            return null;
        }
        HttpSession session = SessionManager.getCurrentSession();
        SystemUser result = (SystemUser) session.getAttribute(CURRENT_LOGIN_STORED_ID);
        return result;
    }

    public void authenticate(String userCode, String password) {
        //1.验证账号和密码
        SystemUser user = new SystemUser();
        user.setUserCode(userCode);
        user.setPassword(password);
        SystemUser dbUser = systemUserDAO.getUserByCode(user.getUserCode());
        String where="user_id=#{userId}";
        Map parameters = new HashMap();
        parameters.put("userId", dbUser.getRecordId());
        //String md5 = StringUtilsExt.getMd5(user.getPassword());
        if (dbUser == null || !password.equals(dbUser.getPassword())) {
            throwException(ERROR_LOGIN_ACCOUNT);
        }
        if (dbUser.isDisabled()) {
            throwException(ERROR_LOGIN_ACCOUNT_DISABLED);
        }
        //2.更新Session
        setCurrentLogin(dbUser);
        //3.更新数据库
        dbUser.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        dbUser.setOnline(true);

        systemUserDAO.update(dbUser);
/*        List<RoleUser> list =roleUserDAO.getByWhere(where,parameters);
        if(list.size()>0){
            for(RoleUser ru:list){
                SystemRole sr =  systemRoleDAO.getById(ru.getRoleId());
                if(sr.getRoleCode().equals("dianzhanag")||sr.getRoleCode().equals("admin")){

                }

            }
        }*/

    }

    public Boolean  adminAuthenticate(String userCode, String password) {
        //1.验证账号和密码
        SystemUser user = new SystemUser();
        user.setUserCode(userCode);
        user.setPassword(password);
        SystemUser dbUser = systemUserDAO.getUserByCode(user.getUserCode());
        String where="user_id=#{userId}";
        Map parameters = new HashMap();
        parameters.put("userId", dbUser.getRecordId());
        List<RoleUser> list =roleUserDAO.getByWhere(where,parameters);
        if(list.size()>0){
            for(RoleUser ru:list){
                SystemRole sr =  systemRoleDAO.getById(ru.getRoleId());
                if(sr.getRoleCode().equals("dianzhanag")||sr.getRoleCode().equals("admin")){
                    //String md5 = StringUtilsExt.getMd5(user.getPassword());
                    if (dbUser == null || !password.equals(dbUser.getPassword())) {
                        throwException(ERROR_LOGIN_ACCOUNT);
                    }
                    if (dbUser.isDisabled()) {
                        throwException(ERROR_LOGIN_ACCOUNT_DISABLED);
                    }
                    //2.更新Session
                    setCurrentLogin(dbUser);
                    //3.更新数据库
                    dbUser.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
                    dbUser.setOnline(true);

                    systemUserDAO.update(dbUser);
                    return  true;
                }

            }
        }
        return  false;
    }

    public synchronized void setCurrentLogin(SystemUser currentLogin) {
        SessionManager.getCurrentSession().setAttribute(CURRENT_LOGIN_STORED_ID, currentLogin);
    }

    public synchronized void kickOffUser()  {
        SystemUser user = getCurrentLogin();
        this.kickOffUser(user);
    }

    public synchronized void kickOffUser(SystemUser login) {
        HttpSession session = this.getUserSession(login);
        if(session==null){
            return;
        }
        //1.移除Session
        session.removeAttribute(CURRENT_LOGIN_STORED_ID);
        //2.更新数据库
        SystemUser dbItem = this.systemUserDAO.getById(login.getRecordId());
        if(dbItem==null){
            return;
        }

        dbItem.setOnline(false);
        dbItem.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
        systemUserDAO.update(dbItem);
    }

    public static HttpSession getUserSession(SystemUser user) {
        HashSet sessions = SessionManager.getSessionList();
        Iterator<HttpSession> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            HttpSession session = iterator.next();
            SystemUser theUser = (SystemUser) session.getAttribute(CURRENT_LOGIN_STORED_ID);
            if (theUser == null) {
                continue;
            }
            if (user.getRecordId().equals(theUser.getRecordId())) {
                return session;
            }
        }
        return null;
    }

    protected static final String CURRENT_LOGIN_STORED_ID = "{9D929EBB-1006-4597-A5E0-F159BB93AA60}";

    public SystemUser getUserByCode(String userCode) {
       return systemUserDAO.getUserByCode(userCode);
    }
}
