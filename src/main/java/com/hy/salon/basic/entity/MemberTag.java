package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberTag extends TraceableEntity<Long> {
    private Long recordId ;//
    private Long  memberId ;//
    private Long tagId ;//
}
