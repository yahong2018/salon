package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;
@DataTableConfiguration("member_gift")
@Data
public class MemberGift  extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberId;

    private Long refTransId;

    private Byte transType;

    private Byte giftType;

    private Long gitId;

    private Double qty;

    private Byte giftSubType;

    private Date giftExpiredDate;



}