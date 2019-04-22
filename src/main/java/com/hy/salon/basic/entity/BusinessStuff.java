package com.hy.salon.basic.entity;


import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;
@DataTableConfiguration("business_stuff")
@Data
public class BusinessStuff extends TraceableEntity<Long> {
    private Long recordId;

    private Long refTransId;

    private Byte transType;

    private Long stuffId;

}