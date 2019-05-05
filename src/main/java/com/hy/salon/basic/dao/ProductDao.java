package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.CardBalance;
import com.hy.salon.basic.entity.Product;
import com.hy.salon.stock.entity.ProductStock;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("productDao")
public class ProductDao extends BaseDAOWithEntity<Product> {
    @Resource(name = "cardBalanceDao")
    private CardBalanceDao cardBalanceDao;
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

    public Product getOneProdectForProductId(Long id){
        String where = "product_id=#{id} ";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }

    public Product checkName(Long salonId,String productName){
        String where = "store_id=#{salonId} and product_name=#{productName}";
        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        parameters.put("productName", productName);

        return this.getOne(where, parameters);
    }

    /**
     * x消费
     * @param salonId
     * @param productName
     * @param productSeriesId
     * @return
     */
    public List<Product> getProdectForConsumption(Long salonId,String productName,Long productSeriesId){
        Map parameters = new HashMap();
        String where = "store_id=#{salonId} ";
        if(null != productName && !"".equals(productName)){
            where=where+" and product_name=#{productName}";
            parameters.put("productName", productName);
        }

        if(null != productSeriesId && productSeriesId!=0){
            where=where+" and product_series_id=#{productSeriesId}";
            parameters.put("productSeriesId", productSeriesId);
        }
        parameters.put("salonId", salonId);
        return this.getByWhere(where, parameters);
    }


    public List<Product> getProdectForCondition(Long salonId,String productClass,Long productSeriesId){

        String where = "store_id=#{salonId} ";
        if(null != productClass && !"".equals(productClass)){
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

    public List<Product> getProdectForCondition2(Long salonId,String productClass,Long productSeriesId,String jobLevel){

        String where = "store_id=#{salonId} ";
        if(null != productClass && !"".equals(productClass)){
            where=where+" and product_class=#{productClass}";
        }

        if(null != productSeriesId && productSeriesId!=0){
            where=where+" and product_series_id=#{productSeriesId}";
        }

        Map parameters = new HashMap();
        parameters.put("salonId", salonId);
        parameters.put("productClass", productClass);
        parameters.put("productSeriesId", productSeriesId);

        if(jobLevel.equals("0")){
            return this.getByWhere(where, parameters);
        }else{
            return this.getSqlHelper().getSqlSession().selectList(SQL_GET_PRODUCT_FOR_SALON, parameters);
        }


    }




    public List<Product> getProdectForCondition(Long salonId){

        String where = "store_id=#{salonId} ";


        Map parameters = new HashMap();
        parameters.put("salonId", salonId);

        return this.getByWhere(where, parameters);
    }

    public List<Map<String,Object>> getCountForProduct(Long parentId) {
        Map parameters = new HashMap();
        parameters.put("parentId", parentId);
        return this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_COUNT_FOR_PRODUCT, parameters);
    }


    public ExtJsResult getProductListAppForMenber(long memberId, long storeId, HttpServletRequest request, ListRequestBaseHandler listRequestBaseHandler) {
        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?listRequest.getWhere()+" store_id="+storeId:listRequest.getWhere()+" and "+" store_id="+storeId);
        List<Product> productList=  listRequestBaseHandler.getByRequest(listRequest);
        ExtJsResult er = new ExtJsResult();
        int count = listRequestBaseHandler.getRequestListCount(listRequest);
        JSONArray jsonArray = new JSONArray();
        for(Product product:productList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serviceName",product.getProductName());
            jsonObject.put("price",product.getPrice());
            String where = " card_id = #{card_id} and member_id=#{member_id}";
            Map parameters = new HashMap();
            parameters.put("card_id", product.getRecordId());
            parameters.put("member_id", memberId);
            List<CardBalance> cardBalanceList = cardBalanceDao.getByWhere(where,parameters);

            jsonObject.put("cardBalanceSize",cardBalanceList.size());
            jsonArray.add(jsonObject);
        }
        er.setSuccess(true);
        er.setMsgcode("0");
        er.setMsg("获取成功");
        er.setData(jsonArray);
        er.setTotal(count);
        return er;
    }


    public List<Map<String,Object>> getProductTotal() {
        Map parameters = new HashMap();
        return this.getSqlHelper().getSqlSession().selectList(SQL_GET_PRODUCT_TOTAL, parameters);
    }

    protected final static String SQL_GET_PRODUCT_TOTAL = "com.hy.salon.basic.dao.GET_PRODUCT_TOTAL";

    protected final static String SQL_QUERY_COUNT_FOR_PRODUCT = "com.hy.salon.basic.dao.QUERY_COUNT_FOR_PRODUCT";

    protected final static String SQL_GET_PRODUCT_LIST = "com.hy.salon.basic.dao.GET_PRODUCT_LIST";

    protected final static String SQL_GET_PRODUCT_FOR_SALON = "com.hy.salon.basic.dao.GET_PRODUCT_FOR_SALON";


}
