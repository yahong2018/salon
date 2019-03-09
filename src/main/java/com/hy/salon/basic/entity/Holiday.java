package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;

@DataTableConfiguration("holiday")
@Data
public class Holiday extends TraceableEntity<Long> {
   private Long  storeId;
    private Date day;
}
