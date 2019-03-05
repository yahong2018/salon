package com.hy.salon.stock.dao;

import com.hy.salon.stock.entity.ProductStock;
import com.zhxh.core.data.BaseDAOWithEntity;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productSockDAO")
public class ProductSockDAO extends BaseDAOWithEntity<ProductStock> {

    public List<ProductStock> findProductStockByWarehouseId(Long warehouseId) {
        Map parameters = new HashMap();
        parameters.put("warehouseId", warehouseId);
        String where = "warehouse_id=#{warehouseId}";
        Map listMap = new HashMap();
        listMap.put("where", where);
        return this.getList(listMap, parameters);
    }
}
