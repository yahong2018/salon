package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.NurseLogModel;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("nurseLogModelDao")
public class NurseLogModelDAO extends BaseDAOWithEntity<NurseLogModel> {

    public List<NurseLogModel> getLogModel(Long storeId){
        String where = "store_id=#{storeId} ";
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getByWhere(where, parameters);
    }

}
