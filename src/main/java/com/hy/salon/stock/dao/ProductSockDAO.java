package com.hy.salon.stock.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.CardBalance;
import com.hy.salon.basic.entity.Service;
import com.hy.salon.stock.entity.ProductStock;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
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
