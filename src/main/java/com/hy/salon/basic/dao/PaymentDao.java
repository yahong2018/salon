package com.hy.salon.basic.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hy.salon.basic.entity.*;
import com.hy.salon.basic.vo.Result;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("paymentDao")
public class PaymentDao extends BaseDAOWithEntity<Payment> {


    @Resource(name = "cardBalanceDao")
    private CardBalanceDao cardBalanceDao;
    @Resource(name = "vipSuiteDao")
    private VipSuiteDAO vipSuiteDao;

    @Resource(name = "serviceDao")
    private ServiceDAO serviceDao;

    @Resource(name = "ServiceSuiteDao")
    private ServiceSuiteDAO serviceSuiteDao;

    protected final static String SQL_GET_PAYMENTLIST = "com.hy.salon.basic.dao.GET_PAYMENTLIST";
    public Result getPaymentList(int page,Long memberId,String toDays,Long storeId) {
        Map parameters = new HashMap();
        parameters.put("storeId",storeId);
        if(memberId!=null){
            parameters.put("memberId",memberId);
        }
        if(StringUtils.isNotEmpty(toDays)){
            String days[] =  toDays.split("~");
            String timeStart = days[0];
            String  timeEnd = days[1];
            parameters.put("timeStart", timeStart);
            parameters.put("timeEnd", timeEnd);
        }
        Result result = new Result();

        PageHelper.startPage(page,2);
        List<Map> listMap =  this.getSqlHelper().getSqlSession().selectList(SQL_GET_PAYMENTLIST, parameters);
        JSONArray jsonArray  = new JSONArray();
        for(Map map:listMap){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("recordId",map.get("record_id"));
            jsonObject.put("memberName",map.get("member_name"));

            jsonObject.put("merchandiseType",(int)map.get("merchandise_type")==0?"服务":"产品");
            CardBalance cardBalance = cardBalanceDao.getById((long)map.get("card_balance_id"));
            int CardType = cardBalance.getCardType();
            if(CardType==0){//卡类型:0. 充值卡  1.次卡    3.套卡
                jsonObject.put("cardType","充值卡");
                VipSuite vipSuite =  vipSuiteDao.getById(cardBalance.getCardId());
                jsonObject.put("cardName",vipSuite.getSuiteName());
            }else if(CardType ==1){
                jsonObject.put("cardType","次卡");
                Service service =  serviceDao.getById(cardBalance.getCardId());
                jsonObject.put("cardName",service.getServiceName());
            }else if(CardType ==2){
                jsonObject.put("cardType","套卡");
                ServiceSuite serviceSuite =  serviceSuiteDao.getById(cardBalance.getCardId());
                jsonObject.put("cardName",serviceSuite.getSuiteName());
            }
            jsonObject.put("paymentType",(int)map.get("payment_type")==0?"使用次数":"使用项目券");

            jsonObject.put("qty",map.get("qty"));
            jsonObject.put("price",map.get("price"));
            jsonObject.put("amount",map.get("amount"));

            jsonArray.add(jsonObject);
        }
        PageInfo<Map> pageInfo = new PageInfo<>(listMap);
        result.setMsg("获取成功");
        result.setMsgcode("0");
        result.setSuccess(true);
        result.setData(jsonArray);
        result.setTotal(Integer.parseInt(pageInfo.getTotal()+""));
        return  result;
    }
}
