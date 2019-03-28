package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 *  高德地图返回工具类
 */

@AllArgsConstructor
@Getter
@Setter
public class RequestResult {


    /** 状态码 */
    private int code;

    /** 返回body */
    private  String body;




}
