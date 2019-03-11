package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("work_summary")
@Getter
@Setter
public class WorkSummary extends TraceableEntity<Long> {

    private Long recordId;
    private Long stuffId ;//员工 id
    private String summary  ;//本月总结
    private String plan    ;//下月计划
    private Date curMonth ;// 报告的当前年月
    private Integer summaryType ;// 总结类型 0 月总结

}
