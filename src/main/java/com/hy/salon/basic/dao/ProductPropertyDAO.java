package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Pictures;
import com.hy.salon.basic.entity.ProductProperty;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productPropertyDAO")
public class ProductPropertyDAO extends BaseDAOWithEntity<ProductProperty> {

    public List<ProductProperty> getProductProperty(Long propertyType){
        String where = "property_type=#{propertyType} ";
        Map parameters = new HashMap();
        parameters.put("propertyType", propertyType);

        return this.getByWhere(where, parameters);
    }
}
