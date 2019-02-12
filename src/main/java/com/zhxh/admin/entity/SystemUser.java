package com.zhxh.admin.entity;

import com.zhxh.core.data.DataCode.BCode;
import com.zhxh.core.data.EntityObject;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@DataTableConfiguration("system_user")
@Getter
@Setter
public class SystemUser extends EntityObject<Long> {
    public final static String DEFAULT_PASSWORD = "888888";

    private String userCode;
    private String userName;
    private String password;
    private int userStatus;
    private String email;
    private boolean online;
    private Timestamp lastLoginTime;

    public boolean isDisabled() {
        return this.userStatus == BCode.STATUS_DISABLED;
    }
}
