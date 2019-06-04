package com.hy.salon.basic.entity;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import lombok.Data;
@Data
public class Token {
	 // 获取到的凭证
    private String token;
    // 获取到ticket
    private String ticket;
    // 凭证有效时间，单位：秒
    private int expiresIn;
    // 添加时间
    private long addTime;

}

