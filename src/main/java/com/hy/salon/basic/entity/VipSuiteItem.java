package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("vip_suite_item")
@Getter
@Setter
public class VipSuiteItem extends TraceableEntity<Long> {
    private Long vipSuiteId;

    private Byte recordType;

    private Double discount;



}
