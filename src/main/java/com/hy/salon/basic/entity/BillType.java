package com.hy.salon.basic.entity;

import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("bill_type")
@Getter
@Setter
public class BillType {
    private Long recordId;

    private Byte type;

    private String typeName;
}
