package com.zhxh.admin.vo;

import com.zhxh.admin.entity.RolePrivilege;
import com.zhxh.admin.entity.SystemUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SystemUserWithPrivilege extends SystemUser {
    private List<RolePrivilege> privilegeList;
}
