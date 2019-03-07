package com.hy.salon.basic.vo;

import com.zhxh.core.data.TraceableEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ServiceSeriesVo extends TraceableEntity<Long> {

    private String seriesName;

    private Long parentId;

    private Byte recordStatus;

    private int isChoice;


}
