package com.hy.salon.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@DataTableConfiguration("salon")
@Getter
@Setter
public class Salon extends TraceableEntity<Long> {
    private String salonName;
    private Long parentId;
    private String tel;
    private Long cityId;
    private String address;
    private Boolean door2Door;
    private Integer bedNum;
    private Double area;
    @JsonFormat(pattern="HH：mm")
    private Date timeOpen;
    @JsonFormat(pattern="HH：mm")
    private Date timeClose;
    private String description;
}
