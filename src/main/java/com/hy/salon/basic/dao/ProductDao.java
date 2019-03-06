package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Product;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productDao")
public class ProductDao extends BaseDAOWithEntity<Product> {
    public List<Product> getProductList(Long salonId) {
        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_PRODUCT_LIST, parameters);
    }

    protected final static String SQL_GET_PRODUCT_LIST = "com.hy.salon.basic.dao.GET_PRODUCT_LIST";
}
