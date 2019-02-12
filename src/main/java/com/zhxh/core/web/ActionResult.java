package com.zhxh.core.web;

public class ActionResult {
    private Object entityObject;
    private int resultCode;
    private String resultId;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getResultCode() {
        return resultCode;
    }

    public Object getEntityObject() {
        return entityObject;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setEntityObject(Object entityObject) {
        this.entityObject = entityObject;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }
}
