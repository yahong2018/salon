package com.hy.salon.basic.entity;

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
    private Date timeOpen;
    private Date timeClose;
    private String description;
}
