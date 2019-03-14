package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.ProductSeries;
import com.hy.salon.basic.vo.ServiceVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productSeriesDao")
public class ProductSeriesDAO extends BaseDAOWithEntity<ProductSeries> {

    public ProductSeries getSeriesForName(String name){
        String where = "series_name=#{name}";
        Map parameters = new HashMap();
        parameters.put("name", name);

        return this.getOne(where, parameters);
    }


    public List<Map> getSeriesForName(String name,Long salonId){
        Map parameters = new HashMap();
        parameters.put("seriesName", name);
        parameters.put("salonId", salonId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_PRODUCT_FOR_SERIES, parameters);
    }

    public List<ProductSeries> getSeriesForUser(Long id){
        String where = "store_id=#{id} and parent_id = 0";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getByWhere(where, parameters);
    }

    public List<ProductSeries> getSonSeriesForId(Long id){
        String where = "parent_id = #{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getByWhere(where, parameters);
    }





    protected final static String SQL_GET_PRODUCT_FOR_SERIES = "com.hy.salon.basic.dao.GET_PRODUCT_FOR_SERIES";



}
