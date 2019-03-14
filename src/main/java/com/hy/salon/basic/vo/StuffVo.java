package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StuffVo {
    private Long recordId;
    private String stuffName;//员工名字
    private Integer reservation;//预约数量
    private String role;//员工角色
    private Integer workSummary;//总结数
}
