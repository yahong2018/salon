package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("stuff_job")
@Getter
@Setter
public class StuffJob extends TraceableEntity<Long> {
    private Long stuffId;
    private Long jobId;
}