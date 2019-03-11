package com.hy.salon.basic.vo;

import com.zhxh.core.data.TraceableEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ShiftVo{
    private Long recordId ;
    private Long storeId  ;//门店
    private Integer shiftType;//班次：0.全  1.早   2. 中   3.晚
    private Date timeStart  ;// 开始时间
    private Date timeEnd  ;//结束时间

    private Long stuffId  ;// 员工
    private String stuffName ;// 员工名字
    private Long shiftId  ;//班次： -1 表示休息 0.全  1.早   2. 中   3.晚
    private Date day       ;//日期


}
