package com.zhxh.admin.entity;

import com.zhxh.core.data.EntityObject;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("role_privilege")
@Getter
@Setter
public class RolePrivilege extends EntityObject<Long> {
    private Long programPrivilegeId;
    private Long roleId;
    private String programId;
    private String privilegeCode;

    public RolePrivilege(){}
    public RolePrivilege(Long roleId, ProgramPrivilege programPrivilege){
        this.setRoleId(roleId);
        this.setPrivilegeCode(programPrivilege.getPrivilegeCode());
        this.setProgramId(programPrivilege.getProgramId());
        this.setProgramPrivilegeId(programPrivilege.getRecordId());
    }
}
