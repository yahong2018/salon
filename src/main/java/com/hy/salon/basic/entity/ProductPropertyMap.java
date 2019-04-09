package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("product_property_map")
@Getter
@Setter
public class ProductPropertyMap extends TraceableEntity<Long> {

    private Long productId;

    private Long productPropertyId;
}
