package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("notice")
@Getter
@Setter
public class Notice extends TraceableEntity<Long> {

    private Long recordId;

    private Long salonId;

    private String title;

    private String content;
}
