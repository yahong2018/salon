package com.hy.salon.stock.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("product_stock")
@Getter
@Setter
public class ProductStock extends TraceableEntity<Long> {
    private int movementType;
    private int serialNumber;
    private int productId;
    private int movementQty;
    private int dateOfManufacture;
    private int purchaseCost;
    private int operatorId;
    private int storeWarehouseId;
    private int referenceRecordNo;
    private int remark;
}
