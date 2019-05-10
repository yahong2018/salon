package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@DataTableConfiguration("reservation")
@Getter
@Setter
public class Reservation extends TraceableEntity<Long> {
    private Long memberId ;         //会员
    private Long stuffId ;          // 预约美容师
    private Long roomId  ;          // 预约的房间
    private Date timeStart ;        // 开始时间
    private Date timeEnd ;          // 结束时间
    private Integer recordStatus  ; // 记录状态：0.未开始  1.已确认    2.服务中    3.已完成
    private String remark;//备注
    private Date date_;
    private Float duration;//时长
    private Byte memberSourc;//会员来源


    private String stuffName;
    private String memberName;
}
