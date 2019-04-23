package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("stuff_score_record")
@Getter
@Setter
public class StuffScoreRecord extends TraceableEntity<Long> {

    private Long stuffId;

    private String matter;

    private Long getPoint;

    private Long getById;
}
