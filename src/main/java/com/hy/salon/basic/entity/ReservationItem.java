package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

@DataTableConfiguration("reservation_item")
@Data
public class ReservationItem extends TraceableEntity<Long> {
    private Long recordId;

    private Long reservationId;

    private Long serviceId;

}