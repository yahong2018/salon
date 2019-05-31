package com.hy.salon.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@DataTableConfiguration("stuff_score_record")
@Getter
@Setter
public class StuffScoreRecord extends TraceableEntity<Long> {

    private Long stuffId;

    private String matter;

    private Long score;

    private Long totalScore;

    private Long issuedBy;

    private String jobName;

    private String stuffName;

    private String salonName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createDate;

    private String picUrl;

    private Integer status;


}
