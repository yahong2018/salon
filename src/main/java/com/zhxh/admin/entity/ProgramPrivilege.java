package com.zhxh.admin.entity;

import com.zhxh.core.data.EntityObject;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import static com.zhxh.core.data.DataCode.BCode.*;


@DataTableConfiguration("program_privilege")
@Getter
@Setter
public class ProgramPrivilege extends EntityObject<Long> {
    private String programId;
    private String privilegeCode;
    private String privilegeName;

    public boolean isInsertPrivilege() {
        return PRIVILEGE_INSERT.equals(this.privilegeCode);
    }

    public boolean isUpdatePrivilege() {
        return PRIVILEGE_UPDATE.equals(this.privilegeCode);
    }

    public boolean isDeletePrivilege() {
        return PRIVILEGE_DELETE.equals(this.privilegeCode);
    }

    public boolean isRunPrivilege() {
        return PRIVILEGE_RUN == this.privilegeCode;
    }
}
