package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@DataTableConfiguration("service")
@Getter
@Setter
public class Service extends TraceableEntity<Long> {


    private Long recordId;

    private Long storeId;

    private String serviceName;

    private Long serviceSeriesId;

    private Byte cardType;

    private Byte recordStatus;

    private Double expiredTime;

    private Integer periodPerTime;

    private Double pricePerTime;

    private Double priceMarket;

    private Double price;

    private Integer returnVisit;

    private Integer timeTotal;

    private String description;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private int optLock;

}
