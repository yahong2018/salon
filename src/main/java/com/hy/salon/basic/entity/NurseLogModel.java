package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("nurse_log_model")
@Getter
@Setter
public class NurseLogModel extends TraceableEntity<Long> {
    private Long recordId ;
    private String modelContent ;//模版内容
}
