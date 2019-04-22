package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;
@DataTableConfiguration("card_balance")
@Data
public class CardBalance extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberId;

    private Long cardId;

    private Byte cardType;

    private Double balance;

    private Byte cardStatus;

    private Date dateExpired;

    private Long parentId;
    private String remark;
}