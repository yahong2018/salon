package com.hy.salon.basic.entity;

import com.zhxh.core.data.EntityObject;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("role_action")
@Getter
@Setter
public class RoleAction extends EntityObject<Long> {

    private Long recordId;

    private Long stuffId;

    private Long systemUserId;

}
