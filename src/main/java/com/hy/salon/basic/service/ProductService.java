package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ProductDao;
import com.hy.salon.basic.entity.Product;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productService")
public class ProductService {
    @Resource(name = "productDao")
    private ProductDao productDao;

    public List<Product> getProductList(Long salonId) {
        return productDao.getProductList(salonId);
    }
}
