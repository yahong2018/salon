package com.hy.salon.basic.entity;

import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("stuff_score")
@Getter
@Setter
public class StuffScore {
    private Long recordId;

    private Long stuffId;

    private Long existing;
}
