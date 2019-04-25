package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;

@Data
@DataTableConfiguration("member_product_keep_item")
public class MemberProductKeepItem extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberProductKeepId;

    private Long productId;

    private Double price;

    private Double qtyPurchased;

    private Double amount;

    private Double qtyReceived;

    private byte productGetType;

}