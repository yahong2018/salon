package com.hy.salon.basic.common.handler;


import lombok.ToString;

import java.util.List;


@ToString
public class AjaxResult {

    private static final String OK = "ok";
    private static final String ERROR = "failed";
    private static final String SUCCESS = "success";

    private String message;
    private String status;
    private Object data;

    public AjaxResult(List<User> list) {}

    public AjaxResult() {}

    public AjaxResult success() {
        this.message = SUCCESS;
        this.status = OK;
        return this;
    }

    public AjaxResult success(Object data) {
        success();
        this.data = data;
        return this;
    }

    public AjaxResult failure() {
        this.status = ERROR;
        return this;
    }

    public AjaxResult failure(Object data){
        this.data = data;
        return this;
    }

    public AjaxResult failure(String message, Object data) {
        this.data = data;
        this.message = message;
        this.status = ERROR;
        return this;
    }

    public AjaxResult failure(String message) {
        this.message = message;
        this.status = ERROR;
        return this;
    }

   /* public Meta getMeta() {
        return meta;
    }*/

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

   /* public class Meta {

        private boolean status;
        private String message;

        public Meta(boolean success) {
            this.status = success;
        }

        public Meta(boolean success, String message) {
            this.status = success;
            this.message = message;
        }

        public boolean isStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }*/

}
