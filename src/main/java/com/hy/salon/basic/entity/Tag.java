package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag  extends TraceableEntity<Long> {
    private Long recordId ;
    private Integer recordType ;//0.功效标签   1.会员标签
    private String tagName;//标签
}
