package com.hy.salon.basic.dao;

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

    public List<Map<String,Object>> getPropertyName(Long productId,Byte propertyType ) {
        Map parameters = new HashMap();
        parameters.put("productId", productId);
        parameters.put("propertyType", propertyType);
        return this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_PROPERTYNAME, parameters);
    }

    protected final static String SQL_QUERY_PROPERTYNAME = "com.hy.salon.basic.dao.QUERY_PROPERTYNAME";

}
