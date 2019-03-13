package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@DataTableConfiguration("service_series")
@Getter
@Setter
public class ServiceSeries extends TraceableEntity<Long> {

    private Long recordId;

    private Long storeId;

    private String seriesName;

    private Long parentId;

    private Byte recordStatus;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private int optLock;

}
