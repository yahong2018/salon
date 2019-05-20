package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@DataTableConfiguration("approval_record")
@Getter
@Setter
public class ApprovalRecord extends TraceableEntity<Long> {

    private String approvalOpinion;

    private Byte approvalStatus;

    private Date approvalDate;

    private Integer approvalType;

    private Integer severalApprovals;

    private Long severalApprovalsStuffId;

    private Integer isLast;

    private Long submitApprovalId;
}
