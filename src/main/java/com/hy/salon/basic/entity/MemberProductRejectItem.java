package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;
@Data
@DataTableConfiguration("member_product_reject_item")
public class MemberProductRejectItem extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberProductRejectId;

    private Long productKeepItemId;

    private Byte rejectType;

    private Double qtyReject;

    private Double amountReject;

    private Byte typeAmountReturn;

}