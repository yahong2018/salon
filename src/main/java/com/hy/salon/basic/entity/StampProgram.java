package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;

@DataTableConfiguration("stamp_program")
@Data
public class StampProgram extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberId;

    private Double denomination;

    private Date expiredTime;

    private Byte isExpired;

    private Byte isUsed;

}