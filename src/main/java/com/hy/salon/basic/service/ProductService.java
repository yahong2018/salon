package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.ProductDao;
import com.hy.salon.basic.entity.Product;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    public ExtJsResult getProductListAppForMenber(long memberId, long storeId, HttpServletRequest request, ListRequestBaseHandler listRequestBaseHandler) {
        return productDao.getProductListAppForMenber(memberId,storeId,request,listRequestBaseHandler);

    }
}
