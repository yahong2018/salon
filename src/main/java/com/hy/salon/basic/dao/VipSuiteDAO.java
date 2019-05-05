package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.ServiceSeriesVo;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import com.zhxh.core.web.PageListRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("vipSuiteDao")
public class VipSuiteDAO extends BaseDAOWithEntity<VipSuite> {
    @Resource(name = "vipSuiteItemDao")
   private VipSuiteItemDAO vipSuiteItemDAO;
    @Resource(name = "picturesDao")
    private PicturesDAO picturesDao;

    @Resource(name = "cardBalanceDao")
    private CardBalanceDao cardBalanceDao;




    public VipSuite getVipSuiteForId(Long id){
        String where = "record_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }

    public List<VipSuite> getVipSuiteListForId(Long id){
        String where = "store_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getByWhere(where, parameters);
    }
    public ExtJsResult getVipSuiteListIdSystemAppForMenber(long memberId,long storeId,HttpServletRequest request, ListRequestBaseHandler listRequestBaseHandler) {

        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?listRequest.getWhere()+" store_id="+storeId:listRequest.getWhere()+" and "+" store_id="+storeId);
        List<VipSuite> listVipSuite=  listRequestBaseHandler.getByRequest(listRequest);
        ExtJsResult er = new ExtJsResult();
        int count = listRequestBaseHandler.getRequestListCount(listRequest);
        JSONArray jsonArray = new JSONArray();
        //一个门店的所有充值卡
        for(VipSuite vs:listVipSuite){
            JSONObject jsonObject =  getVipSuiteJSONObject(vs,memberId);
            jsonArray.add(jsonObject);
     /*       JSONObject jsonObject = new JSONObject();
            jsonObject.put("suiteName",vs.getSuiteName());
            jsonObject.put("price",vs.getPrice());
            jsonObject.put("description",vs.getDescription());
            jsonObject.put("storeId",vs.getStoreId());
            jsonObject.put("recordId",vs.getRecordId());
            jsonObject.put("vipSuiteStatus",vs.getVipSuiteStatus());

            Map parameterC = new HashMap();
            parameterC.put("member_id", memberId);
            parameterC.put("card_id",vs.getRecordId());
            parameterC.put("card_type",0);
            String rwhereC="member_id=#{member_id} and card_id = #{card_id} and card_type = #{card_type} ";
            CardBalance cardBalance = cardBalanceDao.getOne(rwhereC,parameterC);

            jsonObject.put("cardBalance",0);
            if(cardBalance!=null){
                if(cardBalance.getCardStatus()==0){
                    jsonObject.put("cardBalance",cardBalance.getBalance());//账户余额
                }
            }
            Map parameterP = new HashMap();
            parameterP.put("masterDataId", vs.getRecordId());
            parameterP.put("recordType",1);
            parameterP.put("picType",0);
            String rwhereP="master_data_id=#{masterDataId} and record_type = #{recordType} and pic_type=#{picType}";
            List<Pictures> picturesList = picturesDao.getByWhere(rwhereP,parameterP);
            jsonObject.put("picUrl",picturesList.size()>0?picturesList.get(0).getPicUrl():"");
            List<VipSuiteItem> list = vipSuiteItemDAO.queryVipSuitForId(vs.getRecordId());
            String discountString = "";
            if(list.size()>0){
                JSONArray jsonArrayC = new JSONArray();
                for (VipSuiteItem vsi:list){
                    JSONObject jsonObjectC = new JSONObject();

                   *//* 0.单次折扣  1.疗程折扣  2.产品折*//*
                    if(StringUtils.isNotEmpty(discountString)){
                        discountString = discountString+"、";
                    }
                    if(vsi.getRecordType()==0){
                        discountString = discountString+"单次"+vsi.getDiscount();
                        jsonObjectC.put("单次",vsi.getDiscount());
                    }else if(vsi.getRecordType()==1){
                        discountString = discountString+"疗程卡"+vsi.getDiscount();
                        jsonObjectC.put("疗程卡",vsi.getDiscount());
                    }else if(vsi.getRecordType()==2){
                        discountString = discountString+"产品折"+vsi.getDiscount();
                        jsonObjectC.put("产品折",vsi.getDiscount());
                    }
                    jsonArrayC.add(jsonObjectC);

                }
                jsonObject.put("discountArray",jsonArrayC);
                jsonObject.put("discountString",discountString);
                jsonArray.add(jsonObject);
            }else{
                jsonObject.put("discount",discountString);
                jsonObject.put("discountArray",null);
                jsonArray.add(jsonObject);
            }*/
        }
        er.setData(jsonArray);
        er.setTotal(count);
        return  er;
    }

    public JSONObject getVipSuiteJSONObject(VipSuite vs,long memberId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("suiteName",vs.getSuiteName());
        jsonObject.put("price",vs.getPrice());
        jsonObject.put("description",vs.getDescription());
        jsonObject.put("storeId",vs.getStoreId());
        jsonObject.put("recordId",vs.getRecordId());
        jsonObject.put("vipSuiteStatus",vs.getVipSuiteStatus());

        Map parameterC = new HashMap();
        parameterC.put("member_id", memberId);
        parameterC.put("card_id",vs.getRecordId());
        parameterC.put("card_type",0);
        String rwhereC="member_id=#{member_id} and card_id = #{card_id} and card_type = #{card_type} ";
        CardBalance cardBalance = cardBalanceDao.getOne(rwhereC,parameterC);

        jsonObject.put("cardBalance",0);
        if(cardBalance!=null){
            if(cardBalance.getCardStatus()==0){
                jsonObject.put("cardBalance",cardBalance.getBalance());//账户余额
            }
        }
        Map parameterP = new HashMap();
        parameterP.put("masterDataId", vs.getRecordId());
        parameterP.put("recordType",1);
        parameterP.put("picType",0);
        String rwhereP="master_data_id=#{masterDataId} and record_type = #{recordType} and pic_type=#{picType}";
        List<Pictures> picturesList = picturesDao.getByWhere(rwhereP,parameterP);
        jsonObject.put("picUrl",picturesList.size()>0?picturesList.get(0).getPicUrl():"");
        List<VipSuiteItem> list = vipSuiteItemDAO.queryVipSuitForId(vs.getRecordId());
        String discountString = "";
        if(list.size()>0){
            JSONArray jsonArrayC = new JSONArray();
            for (VipSuiteItem vsi:list){
                JSONObject jsonObjectC = new JSONObject();

                /* 0.单次折扣  1.疗程折扣  2.产品折*/
                if(StringUtils.isNotEmpty(discountString)){
                    discountString = discountString+"、";
                }
                if(vsi.getRecordType()==0){
                    discountString = discountString+"单次"+vsi.getDiscount();
                    jsonObjectC.put("单次",vsi.getDiscount());
                }else if(vsi.getRecordType()==1){
                    discountString = discountString+"疗程卡"+vsi.getDiscount();
                    jsonObjectC.put("疗程卡",vsi.getDiscount());
                }else if(vsi.getRecordType()==2){
                    discountString = discountString+"产品折"+vsi.getDiscount();
                    jsonObjectC.put("产品折",vsi.getDiscount());
                }
                jsonArrayC.add(jsonObjectC);

            }
            jsonObject.put("discountArray",jsonArrayC);
            jsonObject.put("discountString",discountString);
            //jsonArray.add(jsonObject);
        }else{
            jsonObject.put("discount",discountString);
            jsonObject.put("discountArray",null);
           // jsonArray.add(jsonObject);
        }
        return jsonObject;
    }

    public ExtJsResult getVipSuiteListIdSystem(long storeId,HttpServletRequest request, ListRequestBaseHandler listRequestBaseHandler) {

        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?listRequest.getWhere()+" store_id="+storeId:listRequest.getWhere()+" and "+" store_id="+storeId);
        List<VipSuite> listVipSuite =  listRequestBaseHandler.getByRequest(listRequest);
        ExtJsResult er = new ExtJsResult();
        int count = listRequestBaseHandler.getRequestListCount(listRequest);
        JSONArray jsonArray = new JSONArray();

        for(VipSuite vs:listVipSuite){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("suiteName",vs.getSuiteName());
            jsonObject.put("price",vs.getPrice());
            jsonObject.put("description",vs.getDescription());
            jsonObject.put("storeId",vs.getStoreId());
            jsonObject.put("recordId",vs.getRecordId());
            jsonObject.put("vipSuiteStatus",vs.getVipSuiteStatus());

            List<VipSuiteItem> list = vipSuiteItemDAO.queryVipSuitForId(vs.getRecordId());
            JSONArray jsonArrayC = new JSONArray();
            for (VipSuiteItem vsi:list){
                JSONObject jsonObjectC = new JSONObject();
                jsonObjectC.put("vipSuiteId",vsi.getVipSuiteId());
                jsonObjectC.put("recordType",vsi.getRecordType());
                jsonObjectC.put("discount",vsi.getDiscount());
//                jsonObjectC.put("discountTime",vsi.getDiscountTime());
//                jsonObjectC.put("discountPeriod",vsi.getDiscountPeriod());
//                jsonObjectC.put("discountProduction",vsi.getDiscountProduction());
                jsonArrayC.add(jsonObjectC);
            }
            jsonObject.put("VipSuiteItemList",jsonArrayC);
            jsonArray.add(jsonObject);
        }
        er.setData(jsonArray);
        er.setTotal(count);
        return  er;
    }

    public List<Map<String,String>> getServiceSeriesForVip(Long vipSuiteId,Long recordType) {
        Map parameters = new HashMap();
        parameters.put("vipSuiteId", vipSuiteId);
        parameters.put("recordType", recordType);

        return this.getSqlHelper().getSqlSession().selectList(SQL_QUERY_BIN_SERVICE_FOR_VIP, parameters);
    }


    protected final static String SQL_QUERY_BIN_SERVICE_FOR_VIP = "com.hy.salon.basic.dao.QUERY_BIN_SERVICE_FOR_VIP";

    public ExtJsResult getServiceListAppForMenber(long memberId, long storeId, HttpServletRequest request, ListRequestBaseHandler listRequestBaseHandler) {
        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?listRequest.getWhere()+" store_id="+storeId:listRequest.getWhere()+" and "+" store_id="+storeId);
        List<Service> serviceList=  listRequestBaseHandler.getByRequest(listRequest);
        ExtJsResult er = new ExtJsResult();
        int count = listRequestBaseHandler.getRequestListCount(listRequest);
        JSONArray jsonArray = new JSONArray();
        for(Service service:serviceList){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("serviceName",service.getServiceName());
            jsonObject.put("price",service.getPrice());
            String where = " card_id = #{card_id} and member_id=#{member_id}";
            Map parameters = new HashMap();
            parameters.put("card_id", service.getRecordId());
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
}
