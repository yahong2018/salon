package com.hy.salon.stock.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("product_stock")
@Getter
@Setter
public class ProductStock extends TraceableEntity<Long> {
    private Long recordId;
    private Long productId;//产品
    private Long warehouseId;//所在仓库/门店
    private Integer stockQty;//在库总数
    private Double cost;//占用成本
}
