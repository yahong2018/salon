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

    public List<StockTransferApplication> getStockTransferApplication(Byte recordStatus,Long recordId,String startTime,String endTime) {
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        parameters.put("recordStatus", recordStatus);
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        String where = "in_warehouse_id= #{recordId} ";
        if(null !=recordStatus && !"".equals(recordStatus)){
            where=where+"and record_status= #{recordStatus} ";
        }
        if(null!=startTime && !"".equals(startTime)){
            where=where+" and create_date > #{startTime} and create_date < #{endTime}";
        }

        return this.getByWhere(where, parameters);
    }

    public StockTransferApplication queryOneStock(Long recordId){
        String where="record_id=#{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", recordId);
        return this.getOne(where,parameters);

    }


}
