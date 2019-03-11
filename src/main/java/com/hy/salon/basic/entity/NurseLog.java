package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("nurse_log")
@Getter
@Setter
public class NurseLog extends TraceableEntity<Long> {
    private Long recordId ;
    private Long stuffId  ;// 员工
    private Long memberId  ;//会员
    private String logContent;//内容：里面包含有文字、表情(emoj)
    private Integer logType   ;//日志类型 0 回访日志 1 护理日志
}
