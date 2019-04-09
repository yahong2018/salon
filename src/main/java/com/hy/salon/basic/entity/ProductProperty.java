package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("product_property")
@Getter
@Setter
public class ProductProperty extends TraceableEntity<Long> {
    private String propertyName;

    private Byte propertyType;
}
