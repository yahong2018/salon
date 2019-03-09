package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TimeSheetVo {
    private Long recordId;
    private Long stuffId ;//员工信息
    private Date day      ;//日期
    private Date timeStart;//上班时间
    private Date timeEnd   ;// 下班时间
    private Integer timeSheetType;//出勤类型：0.正常  1.迟到   2.早退    3. 缺勤（旷工、休假）

    private String salonName;//店名字
    private Long attendance;//出勤
    private Long attendanceNum;//共出勤
    private Long attendanceE;//异常
}
