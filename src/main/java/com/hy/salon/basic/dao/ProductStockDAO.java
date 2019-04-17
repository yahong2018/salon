package com.hy.salon.basic.dao;

import com.hy.salon.stock.entity.ProductStock;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("productStockDAO")
public class ProductStockDAO extends BaseDAOWithEntity<ProductStock> {

    public ProductStock getOneProdectStockForId(Long productId){
        String where = "product_id=#{productId} ";
        Map parameters = new HashMap();
        parameters.put("productId", productId);
        return this.getOne(where, parameters);
    }

}
