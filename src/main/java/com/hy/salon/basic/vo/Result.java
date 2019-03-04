package com.hy.salon.basic.vo;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class Result<T> implements Serializable {
    private String msgcode;
    private String msg;
    private Boolean success;
    private T data;

}
