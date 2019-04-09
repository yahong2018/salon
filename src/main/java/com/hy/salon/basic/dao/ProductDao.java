package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.Product;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productDao")
public class ProductDao extends BaseDAOWithEntity<Product> {
    public List<Product> getProductList(Long salonId) {
        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_PRODUCT_LIST, parameters);
    }


    public Product getOneProdectForId(Long id){
        String where = "record_id=#{id} ";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }





    public List<Product> getProdectForCondition(Long salonId,Long productClass,Long productSeriesId){

        String where = "store_id=#{salonId} ";
        if(null != productClass && productClass!=0){
            where=where+" and product_class=#{productClass}";
        }

        if(null != productSeriesId && productSeriesId!=0){
            where=where+" and product_series_id=#{productSeriesId}";
        }

        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        parameters.put("productClass", productClass);
        parameters.put("productSeriesId", productSeriesId);

        return this.getByWhere(where, parameters);
    }



    public List<Product> getProdectForCondition(Long salonId){

        String where = "store_id=#{salonId} ";


        Map parameters = new HashMap();
        parameters.put("salonId", salonId);

        return this.getByWhere(where, parameters);
    }

    protected final static String SQL_GET_PRODUCT_LIST = "com.hy.salon.basic.dao.GET_PRODUCT_LIST";
}
