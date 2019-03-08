package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//考勤表
@DataTableConfiguration("time_sheet")
@Getter
@Setter
public class TimeSheet extends TraceableEntity<Long> {
    private Long recordId;
    private Long stuffId ;//员工信息
    private Date day      ;//日期
    private Date timeStart;//上班时间
    private Date timeEnd   ;// 下班时间
    private Integer timeSheetType;//出勤类型：0.正常  1.迟到   2.早退    3. 缺勤（旷工、休假）

}
