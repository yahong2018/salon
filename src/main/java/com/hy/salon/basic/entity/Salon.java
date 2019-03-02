package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@DataTableConfiguration("salon")
@Getter
@Setter
public class Salon extends TraceableEntity<Long> {

    private Long recordId;

    private String salonName;

    private Long parentId;

    private String tel;

    private Long cityId;

    private String address;

    private Boolean door2Door;

    private Integer bedNum;

    private Double area;

    private Date timeOpen;

    private Date timeClose;

    private String description;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private int optLock;


}
