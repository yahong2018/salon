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
        String where="create_by = #{createId} and parent_id = 0";
        Map parameters = new HashMap();
        parameters.put("createId", createId);
        return this.getByWhere(where,parameters);
    }

    public List<ServiceSeries> getServiceSeriesForId(Long Id){
        String where="parent_id = #{recordId}";
        Map parameters = new HashMap();
        parameters.put("recordId", Id);
        return this.getByWhere(where,parameters);

    }

}
