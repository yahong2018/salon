package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("stock_transfer_application")
@Getter
@Setter
public class StockTransferApplication extends TraceableEntity<Long> {
    private  Long recordId ;
    private String applicationNo ;
    private Long outWarehouseId ;//调出仓库/门店
    private String remarkApplication;//申请原因
    private String remarkAudit  ;//审批原因
    private Integer recordStatus ;//记录状态： 4.已完成   3.已批准   2.已驳回   1.已撤销  0.已提交(注意，一旦提交，就不可以修改，只能撤销以后重新提交)
}
