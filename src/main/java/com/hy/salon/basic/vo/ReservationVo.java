package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservationVo {
    private Long recordId;//门店id
    private String salonName;//门店名称
    private Integer reservation;//预约数量

    private Long memberId ;         //会员
    private String memberName ;     //会员名字
    private Long stuffId ;          // 预约美容师
    private Long roomId  ;          // 预约的房间
    private Date timeStart ;        // 开始时间
    private Date timeEnd ;          // 结束时间
    private Integer recordStatus  ; // 记录状态：0.未开始  1.已确认    2.服务中    3.已完成
}
