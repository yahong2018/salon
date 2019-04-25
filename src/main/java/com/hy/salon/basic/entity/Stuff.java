package com.hy.salon.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Long storeId;
    private String stuffName;
    private String tel;
    private Timestamp entryTime;
    private Double workAge;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp birthDay;
    private int gender;
    private String special;
    private String dream;
    private String weixin;
    private String qq;
    private String address;
    private String picUrl;
    private String jobName;

    private String jobLevel;

    private int inOffice;

    private Long existing;

    private String storeName;

}
