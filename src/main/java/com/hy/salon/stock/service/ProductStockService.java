package com.hy.salon.stock.service;

import com.hy.salon.stock.dao.ProductSockDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("productStockService")
public class ProductStockService {
    @Resource(name = "productSockDAO")
    private ProductSockDAO productSockDAO;


}
