package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("arrearages_record")
@Data
@Getter
@Setter
public class ArrearagesRecord extends TraceableEntity<Long> {
    private Long recordId;

    private Long refTransId;

    private Long memberId;

    private Date arrearagesDate;

    private Byte arrearagesType;

    private Double amountOfRealPay;

    private Double amountPayable;

    private Double amountDept;

    private Double reimbursement;

    private Byte isPaidOff;
}