package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

@Data
@DataTableConfiguration("member_product_keep")
public class MemberProductKeep extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberId;

    private String remark;

    private Long memberSignature;

}