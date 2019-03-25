package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("vip_suite_item_discount_range")
@Getter
@Setter
public class VipSuiteItemDiscountRange extends TraceableEntity<Long> {


    private Long vipSuiteItemId;

    private Long serviceId;


}
