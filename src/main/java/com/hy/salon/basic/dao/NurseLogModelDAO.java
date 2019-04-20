package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.NurseLogModel;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("nurseLogModelDao")
public class NurseLogModelDAO extends BaseDAOWithEntity<NurseLogModel> {

    public List<NurseLogModel> getLogModel(){
        String where = "1=1 ";
        Map parameters = new HashMap();
        return this.getByWhere(where, parameters);
    }

}
