package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("service_suite_item")
@Getter
@Setter
public class ServiceSuiteItem extends TraceableEntity<Long> {

    private Long serviceSuiteId;

    private Long serviceId;

    private Integer times;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;


}
