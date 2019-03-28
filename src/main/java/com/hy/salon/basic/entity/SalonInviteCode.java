package com.hy.salon.basic.entity;

import com.zhxh.core.data.EntityObject;

import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("salon_invite_code")
@Getter
@Setter
public class SalonInviteCode  extends EntityObject<Long> {

    private Long salonId;

    private String inviteCode;

}
