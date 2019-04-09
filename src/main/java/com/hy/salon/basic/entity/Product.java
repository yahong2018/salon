package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("product")
@Getter
@Setter
public class Product extends TraceableEntity<Long> {
    private  Long recordId;
    private  Long storeId;             // 美容院
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
}
