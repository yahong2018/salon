package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.entity.Salon;
import com.zhxh.admin.dao.SystemUserDAO;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component("salonService")
public class SalonService {

    @Resource(name = "salonDao")
    private SalonDao salonDao;

    public List<Salon> getSalon(){

        return salonDao.getSalon();
    }
    public SalonDao getSalonDao() {
        return this.salonDao;
    }

    public Salon getSalonForId(Long id){


 return salonDao.getSalonForId(id);
}

    public List<Salon> getSalonForStoreId2(Long parentId){
        return salonDao.getSalonForStoreId2(parentId);
    }


    public List<Salon> getSalonForCreateId(Long createId){

        return salonDao.getSalonForCreateId(createId);
    }

    public int insert(Salon salon){
        return salonDao.insert(salon);
    }


    public ExtJsResult getSalonForStoreIdSystem(HttpServletRequest request, Long storeId, ListRequestBaseHandler handler) {
            return  salonDao.getSalonForCreateIdSystem(request,storeId,handler);
    }
}
