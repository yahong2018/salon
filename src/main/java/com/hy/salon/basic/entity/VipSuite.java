package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("vip_suite")
@Getter
@Setter
public class VipSuite extends TraceableEntity<Long> {

    private Long storeId;

    private String suiteName;

    private Double price;

    private Byte vipSuiteStatus;

    private String description;

    private String picUrl;

    private String discountData;


}
