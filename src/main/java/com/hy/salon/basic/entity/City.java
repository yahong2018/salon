package com.hy.salon.basic.entity;

import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("city")
@Getter
@Setter
public class City {

    private Long recordId;

    private String cityCode;

    private String cityName;

    private String parentId;

    private String namePath;
}
