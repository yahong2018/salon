package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockTransferApplicationVo {
    private  Long recordId ;
    private String applicationNo ;
    private Long outWarehouseId ;//调出仓库/门店
    private String remarkApplication;//申请原因
    private String remarkAudit  ;//审批原因
    private Integer recordStatus ;//记录状态： 4.已完成   3.已批准   2.已驳回   1.已撤销  0.已提交(注意，一旦提交，就不可以修改，只能撤销以后重新提交)
    private Long productId;//产品
    private Double qty;//调出数量


    private  Long salonId;             // 美容院
    private  String productName;           //-- 产品名称
    private  Long productClass ;        // 产品类型: 0.客装   1.院装   2.易耗品
    private  Long productSeries_id;     // 产品品牌/系列
    private  Double priceMarket;            //市场价
    private  Double price;                   // 优惠价
}
