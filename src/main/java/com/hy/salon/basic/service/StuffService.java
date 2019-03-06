package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Stuff;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("stuffService")
public class StuffService {

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;



    public List<Stuff> getStuffForStoreId(Long storeId){


        return stuffDao.getStuffForStoreId(storeId);
    }



}
