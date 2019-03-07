package com.hy.salon.basic.vo;

import com.zhxh.core.data.TraceableEntity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ServiceVo extends TraceableEntity<Long> {

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

    private Integer times;


}
