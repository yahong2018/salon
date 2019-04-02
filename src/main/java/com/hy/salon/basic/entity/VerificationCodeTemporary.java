package com.hy.salon.basic.entity;

import com.zhxh.core.data.TraceableEntity;
import com.zhxh.core.data.meta.annotation.DataTableConfiguration;
import lombok.Getter;
import lombok.Setter;

@DataTableConfiguration("verification_code_temporary")
@Getter
@Setter
public class VerificationCodeTemporary extends TraceableEntity<Long> {

    private String phoneNo;

    private String verificationCode;

    private int validTime;

    private int effectiveness;


}
