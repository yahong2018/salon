package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("submit_approval")
@Getter
@Setter
public class SubmitApproval  extends TraceableEntity<Long> {


    private String approvalNumber;

    private Date approvalDate;

    private Long approver;

    private String remark;

    private Long approvalProcessId;

    private Long auditPhotos;
}
