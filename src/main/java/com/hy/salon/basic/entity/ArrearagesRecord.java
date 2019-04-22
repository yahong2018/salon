package com.hy.salon.basic.entity;

import com.zhxh.core.data.BaseDAOWithEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;

@DataTableConfiguration("arrearages_record")
@Data
public class ArrearagesRecord extends BaseDAOWithEntity<Long> {
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