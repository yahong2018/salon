package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ServiceSeries;
import com.hy.salon.basic.vo.ServiceSeriesVo;
import com.hy.salon.basic.vo.StockTransferApplicationVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("serviceSeriesDao")
public class ServiceSeriesDAO extends BaseDAOWithEntity<ServiceSeries> {




    public List<ServiceSeries> getServiceSeriesForCreateId(Long storeId){
        String where="store_id = #{storeId} and parent_id = 0";
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getByWhere(where,parameters);
    }

    public List<ServiceSeries> getServiceSeriesForId(Long Id){
        String where="parent_id = #{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", Id);
        return this.getByWhere(where,parameters);

    }

    public List<ServiceSeriesVo> getServiceSeriesVo(Long parentId,Long vipSuiteId,Byte recordType) {
        Map parameters = new HashMap();
        parameters.put("parentId", parentId);
        parameters.put("recordType", recordType);
        parameters.put("vipSuiteId", vipSuiteId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_BIN_SERVICE_SERIES_FOR_PARENTId, parameters);
    }

    public List<ServiceSeriesVo> getServiceSeriesVoForSuite(Long parentId,Long suiteId) {
        Map parameters = new HashMap();
        parameters.put("parentId", parentId);
        parameters.put("suiteId", suiteId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_BINDING_SERVICE_SERIES_FOR_PARENTId, parameters);
    }

    public List<ServiceSeriesVo> getServiceSeriesVo(Long parentId) {
        Map parameters = new HashMap();
        parameters.put("parentId", parentId);

        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_SERVICE_SERIES_FOR_PARENTId, parameters);
    }


    protected final static String SQL_GET_BIN_SERVICE_SERIES_FOR_PARENTId = "com.hy.salon.basic.dao.GET_BIN_SERVICE_SERIES_FOR_PARENTId";

    protected final static String SQL_GET_SERVICE_SERIES_FOR_PARENTId = "com.hy.salon.basic.dao.GET_SERVICE_SERIES_FOR_PARENTId";

    protected final static String SQL_GET_BINDING_SERVICE_SERIES_FOR_PARENTId = "com.hy.salon.basic.dao.GET_BINDING_SERVICE_SERIES_FOR_PARENTId";







}
