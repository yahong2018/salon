package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("job")
@Getter@Setter
public class Job extends TraceableEntity<Long> {
    private String jobName;
    private int jobLevel;
}
