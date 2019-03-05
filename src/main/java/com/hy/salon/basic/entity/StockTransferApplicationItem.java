package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("stock_transfer_application_item")
@Getter
@Setter
public class StockTransferApplicationItem extends TraceableEntity<Long> {
    private Long recordId;
    private Long stockTransferApplicationId;
    private Long productId;//产品
    private Double qty;//调出数量
}
