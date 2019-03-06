package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.StockTransferApplication;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("stockTransferApplicationDao")
public class StockTransferApplicationDao extends BaseDAOWithEntity<StockTransferApplication> {

    public List<StockTransferApplication> getStockTransferApplication(Integer recordStatus) {
        Map parameters = new HashMap();
        parameters.put("recordStatus", recordStatus);
        String where = "record_status=#{recordStatus}";
        Map listMap = new HashMap();
        listMap.put("where", where);
        return this.getList(listMap, parameters);
    }
}
