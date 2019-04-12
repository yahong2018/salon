package com.hy.salon.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date timeCreate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date timeExpired;

    private Byte recordStatus;

    private String description;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private int optLock;

    private String isExpired;

    private String picUrl;


}
