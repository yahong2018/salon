package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.VipSuiteDAO;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;

@Component("vipSuiteService")
public class VipSuiteService {

    @Resource(name="vipSuiteDao")
    private VipSuiteDAO vipSuiteDao;

    public ExtJsResult getVipSuiteListIdSystem(long storeId,HttpServletRequest request,ListRequestBaseHandler listRequestBaseHandler) {

        return  vipSuiteDao.getVipSuiteListIdSystem(storeId,request,listRequestBaseHandler);
    }

    public ExtJsResult getVipSuiteListIdSystemAppForMenber(long memberId,long storeId, HttpServletRequest request, ListRequestBaseHandler listRequestBaseHandler) {
        return  vipSuiteDao.getVipSuiteListIdSystemAppForMenber(memberId,storeId,request,listRequestBaseHandler);
    }

    public ExtJsResult getServiceListAppForMenber(long memberId, long storeId, HttpServletRequest request, ListRequestBaseHandler listRequestBaseHandler) {
        return  vipSuiteDao.getServiceListAppForMenber(memberId,storeId,request,listRequestBaseHandler);
    }
}
