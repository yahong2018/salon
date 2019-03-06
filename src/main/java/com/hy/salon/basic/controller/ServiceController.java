package com.hy.salon.basic.controller;

import com.hy.salon.basic.dao.ServiceDAO;
import com.hy.salon.basic.entity.Service;
import com.hy.salon.basic.entity.ServiceSeries;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hy/basic/service")
@Api(value = "ServiceController| 次卡/服务项目控制器")
public class ServiceController extends SimpleCRUDController<Service> {


    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;


    @Override
    protected BaseDAOWithEntity<Service> getCrudDao() {
        return serviceDao;
    }




}
