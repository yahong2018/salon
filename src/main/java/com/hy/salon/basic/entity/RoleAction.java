package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("role_action")
@Getter
@Setter
public class RoleAction extends TraceableEntity<Long> {

    private Long recordId;

    private Long stuffId;

    private Long systemUserId;

}
