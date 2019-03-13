package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("pictures")
@Getter
@Setter
public class Pictures extends TraceableEntity<Long> {

    private Long masterDataId;

    private Byte recordType;

    private Byte picType;

    private String picUrl;

}
