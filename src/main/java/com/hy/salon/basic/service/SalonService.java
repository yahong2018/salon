package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.SalonDao;
import com.hy.salon.basic.entity.Salon;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("salonService")
public class SalonService {

    @Resource(name = "salonDao")
    private SalonDao salonDao;


    public List<Salon> getSalon(){

        return salonDao.getSalon();
    }


    public Salon getSalonForId(String id){

        return salonDao.getSalonForId(id);
    }


    public List<Salon> getSalonForStoreId(String parentId){
        return salonDao.getSalonForStoreId(parentId);
    }


}
