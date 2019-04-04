package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;
@DataTableConfiguration("operate_log")
@Data
public class OperateLog extends TraceableEntity<Long> {
    private Long recordId;

    private Long optUserId;

    private Long optRoleId;

    private String optAction;

    private Date optDate;

    private String optInfo;

    private Byte optTerminal;

    private String optUrl;

    private Integer optStatu;

    private String optResult;

  }