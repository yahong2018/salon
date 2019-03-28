package com.zhxh.core.web;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yahong on 14-4-15.
 */
@Getter
@Setter
public class ExtJsResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Object data;
    private int total;

    private String msgcode;
    private String msg;
    private Boolean success;

    private List<String> listKey;

/*    public List<String> getListKey() {
		return listKey;
	}

	public void setListKey(List<String> listKey) {
		this.listKey = listKey;
	}

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
    }*/
}
