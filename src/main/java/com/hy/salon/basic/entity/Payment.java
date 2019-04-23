package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;
@DataTableConfiguration("payment")
@Data
public class Payment extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberId;

    private Byte paymentType;

    private String remark;

    private String memberSignature;


}