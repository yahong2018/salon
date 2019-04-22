package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;

@DataTableConfiguration("repayment_record")
@Data
public class RepaymentRecord extends TraceableEntity<Long> {
    private Long recordId;

    private Long arrearagesRecord;

    private Date reimbursementDate;

    private Double reimbursementAmount;

    private Double stillNeedToPay;

    private Byte isPaidOff;


    private Byte methodPayed;

    private String remark;

    private Long memberSignature;
}