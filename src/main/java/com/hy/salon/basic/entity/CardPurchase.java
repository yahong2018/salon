package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;

@DataTableConfiguration("card_purchase")
@Data
public class CardPurchase extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberId;

    private Long cardId;

    private Byte cardType;

    private Double amountMarket;

    private Double amount;

    private Double amountDebit;

    private Double amountPayed;

    private Byte methodPayed;

    private String remark;

    private Long memberSignature;

    private int rechargeType;

}