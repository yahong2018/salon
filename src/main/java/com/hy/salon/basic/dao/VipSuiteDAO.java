package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hy.salon.basic.entity.Stuff;
import com.hy.salon.basic.entity.VipSuite;
import com.hy.salon.basic.entity.VipSuiteItem;
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
    public VipSuite getVipSuiteForId(Long id){
        String where = "record_id=#{id}";
        Map parameters = new HashMap();
        parameters.put("id", id);

        return this.getOne(where, parameters);
    }


    public ExtJsResult getVipSuiteListIdSystem(long storeId,HttpServletRequest request, ListRequestBaseHandler listRequestBaseHandler) {

        ListRequest listRequest = getListRequest(request);
        listRequest.setWhere(listRequest.getWhere()==""?listRequest.getWhere()+" store_id="+storeId:listRequest.getWhere()+" and "+" store_id="+storeId);
        List<VipSuite> listStuff =  listRequestBaseHandler.getByRequest(listRequest);
        ExtJsResult er = new ExtJsResult();
        int count = listRequestBaseHandler.getRequestListCount(listRequest);
        JSONArray jsonArray = new JSONArray();

        for(VipSuite vs:listStuff){
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
        er.setRootProperty(jsonArray);
        er.setTotal(count);
        return  er;
    }
}
