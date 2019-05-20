package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("consume_record")
@Getter
@Setter
public class ConsumeRecord extends TraceableEntity<Long> {

    private String billNo;

    private Byte way;

    private Double cash;

    private Double amount;

    private Long memberId;
}
