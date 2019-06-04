package com.hy.salon.basic.entity;

import com.zhxh.core.data.EntityObject;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

@Data
@DataTableConfiguration("revenue")
public class revenue extends EntityObject<Long> {
    private Long recordId;

    private Long activityDetailInfoId;

    private Long topRecommender;

    private Double topRecommenderCost;

    private Byte profitType;


}