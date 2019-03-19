package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//班次
@DataTableConfiguration("shift")
@Getter
@Setter
public class Shift extends TraceableEntity<Long> {
    private Long recordId ;
    private Long storeId  ;//门店
    private Integer shiftType;//班次：0.全  1.早   2. 中   3.晚
    private  String timeStart  ;// 开始时间

    private String timeEnd  ;//结束时间
}
