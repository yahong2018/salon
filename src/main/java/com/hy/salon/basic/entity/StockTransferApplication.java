package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("stock_transfer_application")
@Getter
@Setter
public class StockTransferApplication extends TraceableEntity<Long> {
    private String applicationNo;

    private Long productStockMovementId;

    private Long inWarehouseProductId;

    private Long outWarehouseId;

    private Long inWarehouseId;

    private String remarkApplication;

    private String remarkAudit;

    private Byte recordStatus;

    private Date timeOfApplication;

    private Long creator;

    private Date approvalTime;

    private Long approver;
}
