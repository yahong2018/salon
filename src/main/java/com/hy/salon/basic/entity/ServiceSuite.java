package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("service_suite")
@Getter
@Setter
public class ServiceSuite extends TraceableEntity<Long> {


    private Long recordId;

    private Long storeId;

    private String suiteName;

    private Double priceMarket;

    private Double price;

    private Date timeCreate;

    private Date timeExpired;

    private Byte recordStatus;

    private String description;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private int optLock;


}
