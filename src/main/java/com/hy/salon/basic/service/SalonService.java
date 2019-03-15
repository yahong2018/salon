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


}
