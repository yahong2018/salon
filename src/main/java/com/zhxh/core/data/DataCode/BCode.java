package com.zhxh.core.data.DataCode;

public final class BCode {
    //
    //通用状态
    //
    public static final int STATUS_NORMAL = 0;                       //0:正常
    public final static int STATUS_ENABLED = 0;                      //0.启用
    public final static int STATUS_DISABLED = 1;                     //1:停用
    public final static int STATUS_OFFLINE = 0;                      //0.离线
    public final static int STATUS_ONLINE = 1;                       //1.在线

    //
    //权限
    //
    public final static String PRIVILEGE_RUN = "RUN";
    public final static String PRIVILEGE_INSERT = "INSERT";
    public final static String PRIVILEGE_UPDATE = "UPDATE";
    public final static String PRIVILEGE_DELETE = "DELETE";

    public final static String PRIVILEGE_ENABLE_USER = "ENABLE_USER";
    public final static String PRIVILEGE_DISABLE_USER = "DISABLE_USER";
    public final static String PRIVILEGE_ASSIGN_ROLE = "ASSIGN_ROLE";
    public final static String PRIVILEGE_RESET_PASSWORD = "RESET_PASSWORD";


    private BCode() {
    }
}
