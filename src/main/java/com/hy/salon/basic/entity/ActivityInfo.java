package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;

@Data
@DataTableConfiguration("activity_info")
public class ActivityInfo extends TraceableEntity<Long> {
    private Long recordId;

    private String activityName;

    private String url;

    private Long fristOriginator;

    private String inviteCode;

    private Date activityDate;


}