package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("member_wallet")
@Getter
@Setter
public class MemberWallet extends TraceableEntity<Long> {

    private Long memberId;

    private Double balanceCash;

    private Double cashCoupon;

    private Double balanceTotal;

    private Double integral;

    private Double debt;

    private Double amountConsumer;

    private Double amountCharge;

}
