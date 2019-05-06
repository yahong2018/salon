package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.NurseLog;
import com.hy.salon.basic.entity.NurseLogModel;
import com.hy.salon.basic.entity.Stuff;
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



    public List<NurseLog> queryLog(Long storeId, String logType,String memberName,String stuffName) {
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        if("".equals(logType)){
            logType=null;
        }
        if("".equals(memberName)){
            memberName=null;
        }
        if("".equals(stuffName)){
            stuffName=null;
        }

        parameters.put("logType", logType);
        parameters.put("memberName", memberName);
        parameters.put("stuffName", stuffName);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_LOG_FOR_STORE_ID, parameters);
    }

    protected final static String SQL_GET_LOG_FOR_STORE_ID = "com.hy.salon.basic.dao.GET_LOG_FOR_STORE_ID";


    public List<NurseLog> getNurseLogForStuffId(Long stuffId,String logType){
        String where="stuff_id = #{stuffId} and log_type =#{logType}";
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        parameters.put("logType", logType);

        return this.getByWhere(where,parameters);
    }





}
