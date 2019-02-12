package com.zhxh.core.web;

import java.io.Serializable;

/**
 * Created by yahong on 14-4-15.
 */
public class ExtJsResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object rootProperty;
    private int total;

    public Object getRootProperty() {
        return rootProperty;
    }

    public void setRootProperty(Object root) {
        this.rootProperty = root;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
