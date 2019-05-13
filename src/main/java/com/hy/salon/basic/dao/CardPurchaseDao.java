package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.*;
import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequest;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("cardPurchaseDao")
public class CardPurchaseDao extends BaseDAOWithEntity<CardPurchase> {
    protected final static String SQL_GET_RECHARGE = "com.hy.salon.basic.dao.GET_RECHARGE";
    public ExtJsResult getSystemRechargeList(String memberName, long storeId, HttpServletRequest request,String toDays,String role) {
        ExtJsResult extJsResult = new ExtJsResult();
        Map parameters = new HashMap();
        parameters.put("storeId",storeId);
        parameters.put("role",role);
        if(StringUtils.isNotEmpty(memberName)){
            parameters.put("memberName",memberName);
        }
        if(StringUtils.isNotEmpty(toDays)){
            String days[] =  toDays.split("~");
            String timeStart = days[0];
            String  timeEnd = days[1];
            parameters.put("timeStart", timeStart);
            parameters.put("timeEnd", timeEnd);
        }
        PageHelper.startPage(Integer.parseInt(request.getParameter("page")),10);
        List<Map> listMap = this.getSqlHelper().getSqlSession().selectList(SQL_GET_RECHARGE, parameters);
        PageInfo<Map> pageInfo = new PageInfo<>(listMap);
        extJsResult.setSuccess(true);
        extJsResult.setMsg("获取成功");
        extJsResult.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
/*        {
            field : 'recordId',
                    title : 'recordId',
                sort : true,
                align : 'center'
        }
              ,
        {
            field : 'memberId',
                    title : '员工id',
                sort : true,
                align : 'center'
        },
        {
            field : 'cardId',
                    title : '卡id',
                sort : true,
                align : 'center'
        }
              , {
            field : 'cardType',
                    title : '卡类型',
                    align : 'center'
        }
              , {
            field : 'amount',
                    title : '充值金额',
                    align : 'center',
        },
        {
            field : 'memberName',
                    title : '会员名称',
                align : 'center',
        }*/
        JSONArray jsonArray = new JSONArray();

        for(Map map :pageInfo.getList()){
            JSONObject jsonObject  = new JSONObject();
            jsonObject.put("recordId",map.get("recordId"));
            jsonObject.put("cardId",map.get("cardId"));
            jsonObject.put("suiteName",map.get("suiteName"));
            jsonObject.put("amount",map.get("amount"));
            jsonObject.put("memberName",map.get("memberName"));
            int cardType = (int)map.get("cardType");//0. 充值卡  1.套卡  2.次卡
            if(cardType==0){
                jsonObject.put("cardType","充值卡");
            }else if(cardType==1){
                jsonObject.put("cardType","套卡");
            }else{
                jsonObject.put("cardType","次卡");
            }
            jsonArray.add(jsonObject);

        }
        extJsResult.setData(jsonArray);
        return extJsResult;
    }

    public Map<String,Object> queryAmount(String startTime,String endTime,String rechargeType,String carType,Long storeId,String methodPayed) {
        Map parameters = new HashMap();
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        parameters.put("rechargeType", rechargeType);
        parameters.put("carType", carType);
        parameters.put("storeId", storeId);
        parameters.put("methodPayed", methodPayed);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_SUM_AMOUNT, parameters);
    }


    public Map<String,Object> queryAmountForStuff(Long stuffId,String rechargeType,String startTime,String endTime) {
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        parameters.put("rechargeType", rechargeType);
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_AMOUNT_FRO_STUFF, parameters);
    }


    public List<CardPurchase> getCardPurchase(Long memberId, String rechargeType){
        String where = "member_id=#{memberId} ";


        if("0".equals(rechargeType)){
            where = where+" and recharge_type in(0,1)";
        }

        Map parameters = new HashMap();
        parameters.put("memberId", memberId);


        return this.getByWhere(where, parameters);
    }



    protected final static String SQL_GET_AMOUNT_FRO_STUFF = "com.hy.salon.basic.dao.GET_AMOUNT_FRO_STUFF";


    protected final static String SQL_GET_SUM_AMOUNT = "com.hy.salon.basic.dao.GET_SUM_AMOUNT";


}
