package com.hy.salon.basic.dao;

import com.hy.salon.basic.entity.CardBalance;
import com.hy.salon.basic.entity.City;
import com.hy.salon.basic.entity.MemberWallet;
import com.zhxh.core.data.BaseDAOWithEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("cardBalanceDao")
public class CardBalanceDao extends BaseDAOWithEntity<CardBalance>{


    public CardBalance getCardForMemberId(Long memberId,Long cardId,Long cardType){
        String where="member_id = #{memberId} and card_id=#{cardId} and card_type = #{cardType}";
        Map parameters = new HashMap();
        parameters.put("memberId", memberId);
        parameters.put("cardId", cardId);
        parameters.put("cardType", cardType);
        return this.getOne(where,parameters);
    }

}
