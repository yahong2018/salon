package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.MemberProductKeepItem;
import com.hy.salon.basic.entity.MemberProductReject;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("memberProductRejectDao")
public class MemberProductRejectDao extends BaseDAOWithEntity<MemberProductReject> {

    protected final static String SQL_GET_REFUNDPRODUCT = "com.hy.salon.basic.dao.GET_REFUNDPRODUCT";
    public Result refundMemberProductKeepList(Long memberProductKeepItem) {
        Result result = new Result();
        Map parameters = new HashMap();
        parameters.put("product_keep_item_id",memberProductKeepItem);
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_REFUNDPRODUCT, parameters);
       // m.record_id,m.remark ,mp.reject_type , mp.qty_reject, mp.amount_reject ,mp.type_amount_return
        JSONArray jsonArray = new JSONArray();
        for(Map map: listMap){
            JSONObject jsonObject  = new JSONObject();
            jsonObject.put("record_id",map.get("record_id"));
            jsonObject.put("remark",map.get("remark"));
            jsonObject.put("reject_type",(int)map.get("reject_type")==0?"未领取退款":"已领取退款");
            jsonObject.put("qty_reject",map.get("qty_reject"));
            jsonObject.put("amount_reject",map.get("amount_reject"));
            jsonObject.put("type_amount_return",(int)map.get("type_amount_return")==0?"现金":"余额");
            jsonArray.add(jsonObject);
        }
        PageInfo<Map> pageInfo = new PageInfo<>(listMap);
        result.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
        result.setData(jsonArray);
        result.setSuccess(true);
        return  result;
    }


    public Map<String,Object> queryRefundAmountForStuff(Long storeId) {
        Map parameters = new HashMap();
        parameters.put("storeId", storeId);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_REFUND_AMOUNT_FRO_STUFF, parameters);
    }


    protected final static String SQL_GET_REFUND_AMOUNT_FRO_STUFF = "com.hy.salon.basic.dao.GET_REFUND_AMOUNT_FRO_STUFF";
}
