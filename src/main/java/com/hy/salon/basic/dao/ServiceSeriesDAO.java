package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ServiceSeries;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("serviceSeriesDao")
public class ServiceSeriesDAO extends BaseDAOWithEntity<ServiceSeries> {


    public List<ServiceSeries> getServiceSeriesForCreateId(Long createId){
        Map parameters = new HashMap();
        parameters.put("createId", createId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SERVICE_SERIES_FOR_CREATEId, parameters);

    }
    public List<ServiceSeries> getServiceSeriesForId(Long Id){
        Map parameters = new HashMap();
        parameters.put("recordId", Id);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SERVICE_SERIES_FOR_Id, parameters);

    }







    protected final static String SQL_GET_SERVICE_SERIES_FOR_Id = "com.hy.salon.basic.dao.GET_SERVICE_SERIES_FOR_Id";

    protected final static String SQL_GET_SERVICE_SERIES_FOR_CREATEId = "com.hy.salon.basic.dao.GET_SERVICE_SERIES_FOR_CREATEId";
}
