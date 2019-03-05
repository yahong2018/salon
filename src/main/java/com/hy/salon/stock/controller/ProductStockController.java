package com.hy.salon.stock.controller;

import com.hy.salon.basic.vo.Result;
import com.hy.salon.stock.dao.ProductSockDAO;
import com.hy.salon.stock.entity.ProductStock;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.SimpleCRUDController;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/hy/salon/sock")
public class ProductStockController extends SimpleCRUDController<ProductStock> {

    @Resource(name = "productSockDAO")
    private ProductSockDAO productSockDAO;

    @Override
    protected BaseDAOWithEntity<ProductStock> getCrudDao() {
        return productSockDAO;
    }
}
