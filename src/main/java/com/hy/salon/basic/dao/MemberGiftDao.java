package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.MemberGift;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("memberGiftDao")
public class MemberGiftDao extends BaseDAOWithEntity<MemberGift> {
    protected final static String SQL_GET_MEMBERGIFT = "com.hy.salon.basic.dao.GET_MEMBERGIFT";
    public ExtJsResult getSystemMemberGiftList(String memberName, Long storeId,  Long refTransId,HttpServletRequest request) {
        ExtJsResult extJsResult = new ExtJsResult();
        Map parameters = new HashMap();
        parameters.put("storeId",storeId);
        if(StringUtils.isNotEmpty(memberName)){
            parameters.put("memberName",memberName);
        }
        parameters.put("refTransId",refTransId);

        PageHelper.startPage(Integer.parseInt(request.getParameter("page")),2);
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_MEMBERGIFT, parameters);
        PageInfo<Map> pageInfo = new PageInfo<>(listMap);
        extJsResult.setSuccess(true);
        extJsResult.setMsg("获取成功");
        extJsResult.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
        List<Map> list = pageInfo.getList();

        JSONArray jsonArray = new JSONArray();
        for(Map map:list){
            JSONObject jsonObject = new JSONObject();
            int transType = (int) map.get("transType");
            jsonObject.put("memberId",map.get("memberId"));
            if(transType==0){
                jsonObject.put("transType","购卡");
            }else{
                jsonObject.put("transType","充值");
            }
            int giftType = (int) map.get("giftType");
            if(giftType==0){
                jsonObject.put("giftType","项目");
            }else if(giftType==1){
                jsonObject.put("giftType","产品");
            }else if(giftType==2){
                jsonObject.put("giftType","优惠券");
            }else{
                jsonObject.put("giftType","金额");
            }
            jsonObject.put("recordId",map.get("recordId"));
            jsonObject.put("memberName",map.get("memberName"));
            jsonObject.put("qty",map.get("qty"));
            jsonObject.put("giftExpiredDate",map.get("giftExpiredDate"));
            jsonArray.add(jsonObject);
        }

        extJsResult.setData(jsonArray);
        return extJsResult;
    }

    public Map<String,Object> getQty(String startTime,String endTime,String giftCashType) {
        Map parameters = new HashMap();
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        parameters.put("giftCashType", giftCashType);
       return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_QTY, parameters);
    }


    protected final static String SQL_GET_QTY = "com.hy.salon.basic.dao.GET_QTY";

}
