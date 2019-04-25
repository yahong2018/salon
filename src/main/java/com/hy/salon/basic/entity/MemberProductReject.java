package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;
@Data
@DataTableConfiguration("member_product_reject")
public class MemberProductReject extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberId;

    private String remark;

    private String memberSignature;


}