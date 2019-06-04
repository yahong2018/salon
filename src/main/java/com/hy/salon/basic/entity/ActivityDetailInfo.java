package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

@Data
@DataTableConfiguration("activity_detail_info")
public class ActivityDetailInfo extends TraceableEntity<Long> {
    private Long recordId;

    private Long activityInfoId;
    private Long memberId;

    private Byte isTopRecommender;

    private Long lastReference;

    private Double totleEarnings;
    private Byte totleRecommender;

}