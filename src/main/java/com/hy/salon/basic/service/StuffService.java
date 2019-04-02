package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Stuff;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("stuffService")
public class StuffService {

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;



    public List<Stuff> getStuffForStoreId(Long storeId){


        return stuffDao.getStuffForStoreId(storeId);
    }


    public ExtJsResult getStuffListStoreIdSystem(HttpServletRequest request, long recordId, ListRequestBaseHandler listRequestBaseHandler) {

        return stuffDao.getStuffListStoreIdSystem(request,recordId,listRequestBaseHandler);
    }
}
