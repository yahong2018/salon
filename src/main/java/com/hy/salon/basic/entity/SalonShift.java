package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SalonShift extends TraceableEntity<Long> {
    private String salonName;
   // private Long parentId;
    private String tel;
    private Long cityId;
    private String address;
//    private Boolean door2Door;
//    private Integer bedNum;
//    private Double area;
//    private Date timeOpen;
//    private Date timeClose;
    private String description;

    private String morningShift;

    private String middleShift;

    private String eveningShift;

    private String allShift;
}
