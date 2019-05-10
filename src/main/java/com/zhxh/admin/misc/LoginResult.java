package com.zhxh.admin.misc;

import com.hy.salon.basic.entity.Job;
import com.hy.salon.basic.entity.Salon;
import com.hy.salon.basic.entity.Stuff;
import com.zhxh.admin.entity.SystemRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResult<T> {
    public final static int LOGIN_CODE_ERROR = -1;
    public final static int LOGIN_CODE_OK = 0;

    private int msgcode;
    private String msg;
    private boolean success;
    private boolean areMember;
    private Long salonId;
    private List<SystemRole> listRole;
    private List<Job> listJob;
    private List<Salon> listSalon;

    private T data;

    private Stuff stuff;

    private String  salonName;


}
