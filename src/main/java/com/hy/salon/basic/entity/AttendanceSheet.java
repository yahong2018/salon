package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@DataTableConfiguration("attendance_sheet")
@Getter
@Setter
public class AttendanceSheet extends TraceableEntity<Long> {

    private Long recordId;

    private Long stuffId;

    private Date attendanceTime;

    private String address;

    private Date createDate;

    private Long createBy;

    private Date updateDate;

    private Long updateBy;

    private int optLock;



}
