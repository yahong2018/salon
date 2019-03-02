package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@DataTableConfiguration("stuff")
    @Getter
    @Setter
    public class Stuff extends TraceableEntity<Long> {

    private Long recordId;
    private String stuffName;
    private String tel;
    private Timestamp entryTime;
    private Double workAge;
    private int gender;
    private String special;
    private String dream;
    private String weixin;
    private String qq;
    private String address;
    private Timestamp createDate;
    private Long createBy;
    private Timestamp updateDate;
    private Long updateBy;
    private int optLock;





}
