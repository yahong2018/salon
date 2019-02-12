package com.zhxh.admin.misc;

import com.zhxh.admin.entity.SystemUser;
import com.zhxh.admin.service.AuthenticateService;
import com.zhxh.core.utils.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

@Component
public class SessionManager implements HttpSessionListener {
    @Resource(name="authenticateService")
    private AuthenticateService authenticateService;

    public SessionManager(){
    }

    private final static HashSet sessions = new HashSet();

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpSession getCurrentSession() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            return null;
        }
        HttpSession session = request.getSession();
        synchronized (sessions) {
            if (!sessions.contains(session)) {
                sessions.add(session);
            }
        }
        return session;
    }

    public static HashSet getSessionList() {
        return sessions;
    }

    protected synchronized void registerSession(HttpSession session) {
        if(!sessions.contains(session)) {
            sessions.add(session);
        }
    }

    protected synchronized void removeSession(HttpSession session) {
        sessions.remove(session);
    }

    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        registerSession(session);
        Logger.debug("sessionCreated，Session数量为：" + sessions.size());
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        synchronized (sessions) {
            if (sessions.contains(session)) {
                SystemUser user = this.authenticateService.getSessionLogin(session);
                if (user != null) {
                    Logger.debug("用户" + user.getUserCode() + "退出");
                    try {
                        authenticateService.kickOffUser(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                removeSession(session);
            }
        }
        Logger.debug("sessionDestroyed，Session数量为：" + sessions.size());
    }
}