package com.zhxh.admin.misc;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {
    public final static int LOGIN_CODE_ERROR = -1;
    public final static int LOGIN_CODE_OK = 0;

    private int code;
    private String message;
}
