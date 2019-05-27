package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@DataTableConfiguration("job")
@Getter
@Setter
public class Job extends TraceableEntity<Long> {
    private String jobName;
    private int jobLevel;
    private Long recordId;
    private Date createDate;
    private Date updateDate;
    private int optLock;
}
