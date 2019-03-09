package com.hy.salon.basic.entity;

import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("stuff_integral")
@Getter
@Setter
public class StuffIntegral {
    private Long recordId ;//
    private Long stuffId ;// 哪个员工
    private Long existing  ;//现有的积分
}
