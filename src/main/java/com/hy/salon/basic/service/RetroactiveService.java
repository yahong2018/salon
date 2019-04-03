package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.RetroactiveDao;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component("retroactiveService")
public class RetroactiveService {
    @Resource(name = "retroactiveDao")
    private RetroactiveDao retroactiveDao;

    public ExtJsResult getPatchCardListByStoreId(HttpServletRequest request, Long recordId, ListRequestBaseHandler listRequestBaseHandler) {
        return retroactiveDao.getPatchCardListByStoreId(request,recordId,listRequestBaseHandler);
    }
}
