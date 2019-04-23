package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;

@Data
@DataTableConfiguration("payment_item")
public class PaymentItem extends TraceableEntity<Long> {
    private Long recordId;

    private Long paymentId;

    private Long cardBalanceId;

    private Long merchandiseId;

    private Byte merchandiseType;

    private Byte paymentType;

    private Double qty;

    private Double price;

    private Double amount;



}