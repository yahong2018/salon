package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("visual_range_mapping")
@Getter
@Setter
public class VisualRangeMapping extends TraceableEntity<Long> {
    private Long recordId;

    private Long noticeId;

    private Long visualRangeId;

}
