package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ProductStockMovement;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productStockMovementDao")
public class ProductStockMovementDao extends BaseDAOWithEntity<ProductStockMovement> {
    public List<ProductStockMovement> getProductBymovementType(Long movementType) {
        Map parameters = new HashMap();
        parameters.put("movementType", movementType);
        String where = "movement_type=#{movementType}";
        Map listMap = new HashMap();
        listMap.put("where", where);
        return this.getList(listMap, parameters);
    }
}
