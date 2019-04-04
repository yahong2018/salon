package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.OperateLogDao;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component("operateLogService")
public class OperateLogService {
    @Resource(name="operateLogDao")
    private OperateLogDao operateLogDao;

    public ExtJsResult getScheduleForStoreIdSystem(HttpServletRequest request,ListRequestBaseHandler listRequestBaseHandler) {
        return operateLogDao.getScheduleForStoreIdSystem(request,listRequestBaseHandler);

    }
}
