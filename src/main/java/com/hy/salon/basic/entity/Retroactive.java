package com.hy.salon.basic.entity;

import com.zhxh.core.data.EntityObject;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;
@DataTableConfiguration("retroactive")
@Data
public class Retroactive extends EntityObject<Long> {
    private Long recordId;

    private Long auditPerson;

    private Long salonId;

    private Date date;

    private String reson;

    private Byte auditStatu;

    private Long userId;

    private String auditOpinion;


    private  int retroactiveType;

}