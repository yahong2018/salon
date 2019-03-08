package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("stuff_integral_record")
@Getter
@Setter
public class StuffIntegralRecord extends TraceableEntity<Long> {
    private Long recordId ;//
    private Long stuffId  ;//哪个员工
    private String metter   ;// 在哪里，做了些什么事
    private Long getPoint  ;//得到的积分总数
    private Long getById  ;//谁给的 ,这个id来源于员工表， 一般是店长或者院长给的

}
