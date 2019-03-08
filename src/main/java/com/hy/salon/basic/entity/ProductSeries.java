package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("product_series")
@Getter
@Setter
public class ProductSeries extends TraceableEntity<Long> {

    private String seriesName;

    private Long parentId;

    private Byte recordStatus;


}
