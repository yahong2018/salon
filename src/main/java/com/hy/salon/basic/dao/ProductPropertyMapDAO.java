package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ProductPropertyMap;
import com.hy.salon.basic.entity.ProductSeries;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productPropertyMapDAO")
public class ProductPropertyMapDAO extends BaseDAOWithEntity<ProductPropertyMap> {


    public List<ProductPropertyMap> getProForId(Long recordId){
        String where = "product_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);

        return this.getByWhere(where, parameters);
    }
}
