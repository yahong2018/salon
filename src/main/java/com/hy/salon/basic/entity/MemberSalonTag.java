package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("member_salon_tag")
@Getter
@Setter
public class MemberSalonTag extends TraceableEntity<Long> {

    private Long salonId;

    private Long tagId;
}
