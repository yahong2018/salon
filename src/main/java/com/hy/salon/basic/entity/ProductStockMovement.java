package com.hy.salon.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("product_stock_movement")
@Getter
@Setter
public class ProductStockMovement extends TraceableEntity<Long> {
//    private Long recordId  ;
//    private Integer recordCreateType  ;     // 记录产生方式：0.手工  1.扫码
//    private Integer movementType  ;          //异动类型：0~63 为入库  64~127为出库 ;
//    // 0. 采购入库  1.调拨入库  2.调整入库/盘盈 64. 内部员工  65.顾客   66. 赠送  67. 报废  68.院用  69.退回供应商  70.下发到店    71.调拨出库  72.调整出库/盘亏
//    private String movementNo  ;             //异动单号
//    private Long productId ;                 // 产品
//    private Integer movementQty ;            // 异动数量
//    private Date dateOfManufacture  ;       // 生产日期
//    private Double purchaseCost ;            //进货单价：入库的时候需要填
//    private Long warehouseId  ;              //发生仓库/门店
//    private String referenceRecordNo ;      //引用单号:采购单/送货单/销售单/调拨申请单/盘点单号调拨：必须要填写调拨申请单，而且要检查调拨申请单是否已经被批准


    private Byte recordCreateType;

    private Byte movementType;

    private String movementNo;

    private Long productId;

    private Integer movementQty;

    private Integer movementPurQty;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfManufacture;

    private Double purchaseCost;

    private Long warehouseId;

    private String referenceRecordNo;

    private String remark;

    private Integer qty;

    private String picUrl;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;
}
