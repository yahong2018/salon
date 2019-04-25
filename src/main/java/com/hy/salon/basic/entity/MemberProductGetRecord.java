package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Data;

import java.util.Date;
@Data
@DataTableConfiguration("member_product_get_record")
public class MemberProductGetRecord extends TraceableEntity<Long> {
    private Long recordId;

    private Long memberProductKeepItem;

    private Double qty;

    private String remark;

    private Long memberSignature;


}