package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class NurseLogVo implements Serializable {
    private Long recordId;//门店id
    private String salonName;//门店名称
    private Integer logType;//0.回访; 1.护理数量
    private Integer nurseLog;//数量
    private Integer task;//任务数
}
