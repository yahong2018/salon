package com.zhxh.admin.vo;

import com.zhxh.admin.entity.ProgramPrivilege;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramPrivilegeVO extends ProgramPrivilege {
    private boolean checked;
    private String dataType;
}
