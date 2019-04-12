package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;
@DataTableConfiguration("tag")
@Getter
@Setter
public class Tag  extends TraceableEntity<Long> {
    private Long recordId ;
    private Integer recordType ;//0.功效标签   1.会员标签
    private String tagName;//标签
    private int memberSize;
}
