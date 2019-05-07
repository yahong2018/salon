package com.hy.salon.basic.dao;


import com.hy.salon.basic.entity.PaymentItem;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("paymentItemDao")
public class PaymentItemDao extends BaseDAOWithEntity<PaymentItem> {


    public Map<String,Object> queryPaymentAmountForStuff(Long stuffId,String startTime,String endTime) {
        Map parameters = new HashMap();
        parameters.put("stuffId", stuffId);
        parameters.put("startTime", startTime);
        parameters.put("endTime", endTime);
        return this.getSqlHelper().getSqlSession().selectOne(SQL_GET_PAYMENT_AMOUNT_FRO_STUFF, parameters);
    }

    protected final static String SQL_GET_PAYMENT_AMOUNT_FRO_STUFF = "com.hy.salon.basic.dao.GET_PAYMENT_AMOUNT_FRO_STUFF";

}
