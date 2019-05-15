package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;
@DataTableConfiguration("member_tag")
@Getter
@Setter
public class MemberTag extends TraceableEntity<Long> {
    private Long recordId ;//
    private Long  memberId ;//
    private Long tagId ;//

    private Member member;
}
