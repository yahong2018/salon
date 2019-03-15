package com.zhxh.admin.misc;

import com.hy.salon.basic.entity.Job;
import com.zhxh.admin.entity.SystemRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResult {
    public final static int LOGIN_CODE_ERROR = -1;
    public final static int LOGIN_CODE_OK = 0;

    private int code;
    private String message;

    private Long salonId;
    private List<SystemRole> listRole;
    private List<Job> listJob;
}
