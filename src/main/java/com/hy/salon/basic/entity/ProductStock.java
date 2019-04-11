package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("product_stock")
@Getter
@Setter
public class ProductStock extends TraceableEntity<Long> {

    private Long productId;

    private Long warehouseId;

    private Integer stockQty;

    private Double cost;
}
