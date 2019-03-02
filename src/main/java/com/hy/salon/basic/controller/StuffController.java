package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.StuffDao;
import com.hy.salon.basic.entity.Stuff;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/stuff")
public class StuffController extends SimpleCRUDController<Stuff> {

    @Resource(name = "stuffDao")
    private StuffDao stuffDao;



    @Override
    protected BaseDAOWithEntity<Stuff> getCrudDao() {
        return stuffDao;
    }


}
