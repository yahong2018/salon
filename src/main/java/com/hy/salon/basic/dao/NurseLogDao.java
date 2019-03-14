package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.NurseLog;
import com.hy.salon.basic.entity.NurseLogModel;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("nurseLogDao")
public class NurseLogDao extends BaseDAOWithEntity<NurseLog> {
    public List<NurseLogModel> getLogModel(Integer pageNo, Integer pageSize) {
        int page=(pageNo-1)*pageSize;
        Map parameters = new HashMap();
        parameters.put("page", page);
        parameters.put("pageSize", pageSize);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_LOG_MODEL, parameters);
    }
    protected final static String SQL_GET_LOG_MODEL = "com.hy.salon.basic.dao.GET_LOG_MODEL";


}
