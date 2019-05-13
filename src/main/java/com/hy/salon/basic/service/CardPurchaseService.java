package com.hy.salon.basic.service;

import com.hy.salon.basic.dao.CardPurchaseDao;
import com.zhxh.core.web.ExtJsResult;
import com.zhxh.core.web.ListRequestBaseHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component("cardPurchaseService")
public class CardPurchaseService {

    @Resource(name="cardPurchaseDao")
    private CardPurchaseDao cardPurchaseDao;
    public ExtJsResult getSystemRechargeList(String memberId, long storeId, HttpServletRequest request ,String toDays,String role) {
        return cardPurchaseDao.getSystemRechargeList(memberId,storeId,request,toDays,role);

    }
}
