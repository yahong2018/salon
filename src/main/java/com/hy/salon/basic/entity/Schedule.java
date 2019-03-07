package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//排班信息
@DataTableConfiguration("schedule")
@Getter
@Setter
public class Schedule extends TraceableEntity<Long> {
    private Long recordId ;
    private Long stuffId  ;// 员工
    private Long shiftId  ;//班次： -1 表示休息 0.全  1.早   2. 中   3.晚
    private Date day       ;//日期

}
