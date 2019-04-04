package com.hy.salon.basic.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OperateLog {
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