package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationVo {
    private Long recordId;//门店id
    private String salonName;//门店名称
    private Integer reservation;//预约数量
}
