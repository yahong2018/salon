package com.hy.salon.basic.vo;

import com.zhxh.core.data.TraceableEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductStockMovementVo extends TraceableEntity<Long> {
    private Long recordId  ;
    private Integer recordCreateType  ;     // 记录产生方式：0.手工  1.扫码
    private Integer movementType  ;          //异动类型：0~63 为入库  64~127为出库 ;
    // 0. 采购入库  1.调拨入库  2.调整入库/盘盈 64. 内部员工  65.顾客   66. 赠送  67. 报废  68.院用  69.退回供应商  70.下发到店    71.调拨出库  72.调整出库/盘亏
    private String movementNo  ;             //异动单号
    private Long productId ;                 // 产品
    private Integer movementQty ;            // 异动数量
    private Date dateOfManufacture  ;       // 生产日期
    private Double purchaseCost ;            //进货单价：入库的时候需要填
    private Long warehouseId  ;              //发生仓库/门店
    private String referenceRecordNo ;      //引用单号:采购单/送货单/销售单/调拨申请单/盘点单号调拨：必须要填写调拨申请单，而且要检查调拨申请单是否已经被批准


    private  Long salonId;             // 美容院
    private  String productName;           //-- 产品名称
    private  Long productClass ;        // 产品类型: 0.客装   1.院装   2.易耗品
    private  Long productSeriesId;     // 产品品牌/系列
    private  Double priceMarket;            //市场价
    private  Double price;                   // 优惠价
    private  String productCode ;          // 产品编号
    private  Long specification ;         //规格:就是数量，比如3kg/瓶
    private  Long specificationUnit   ;   //规格单位：0. g(克)   1.Kg(千克)  2.ml(毫升)  3.L(升)
    private  Long productUnitId   ;       //单位：瓶/袋/包等
    private  String barCode  ;                // 二维码/条形码
    private  Long shelfLife  ;             //保质期(月)
    private  Long dayOfPreWarning;      //产品有效期预警（天）
    private  Long stockOfPreWarning  ;   //库存预警数量
    private  Integer recordStatus;            //记录状态：0.启用   1. 停用
    private  String description;

    private Integer stockQty;//在库总数
    private Double cost;//占用成本

}
