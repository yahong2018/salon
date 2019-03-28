package com.hy.salon.basic.vo;

import lombok.Data;

@Data
public class MyResult extends Result {

   private int attendanceNum;//应出勤数
    private int hasAttendanceNum ;//出勤数
    private int yichangNum;//异常数
}
