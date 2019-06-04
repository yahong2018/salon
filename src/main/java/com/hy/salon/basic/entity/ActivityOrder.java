package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

@Data
@DataTableConfiguration("activity_order")
public class ActivityOrder  extends TraceableEntity<Long> {
    private Long recordId;

    private String orderNo;

    private Long activityDetailInfoId;

    private Double orderMoney;


}