package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("product")
@Getter
@Setter
public class Product extends TraceableEntity<Long> {

    private  Long storeId;

    private String productName;

    private String productClass;

    private Long productSeriesId;

    private Double priceMarket;

    private Double price;

    private String productCode;

    private Integer specification;

    private Byte specificationUnit;

    private Long productUnitId;

    private String barCode;

    private Byte shelfLife;

    private Integer dayOfPreWarning;

    private Integer stockOfPreWarning;

    private Byte recordStatus;

    private String description;

    private String specificationsName;

    private String companyName;

    private String picUrl;

    private String stockQty;

    private String specifications;

}
