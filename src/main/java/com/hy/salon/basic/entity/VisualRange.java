package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("visual_range")
@Getter
@Setter
public class VisualRange extends TraceableEntity<Long> {
    private Long recordId;

    private Long stuffId;

    private Integer statu;

}
