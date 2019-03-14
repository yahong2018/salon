package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalonVo {
    private Long recordId;//门店id
    private String salonName;//门店名称
    private Integer stuffNum;//员工总数
    private Integer workSummary;//已写总结数
}
