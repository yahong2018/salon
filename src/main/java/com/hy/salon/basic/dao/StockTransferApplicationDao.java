package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Service;
import com.hy.salon.basic.entity.StockTransferApplication;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("stockTransferApplicationDao")
public class StockTransferApplicationDao extends BaseDAOWithEntity<StockTransferApplication> {

    public List<StockTransferApplication> getStockTransferApplication(Byte recordStatus,Long recordId) {
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        parameters.put("recordStatus", recordStatus);
        String where = "record_status=#{recordStatus} and in_warehouse_id=#{recordId}";
        Map listMap = new HashMap();
        listMap.put("where", where);
        return this.getList(listMap, parameters);
    }

    public StockTransferApplication queryOneStock(Long recordId){
        String where="record_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getOne(where,parameters);

    }


}
