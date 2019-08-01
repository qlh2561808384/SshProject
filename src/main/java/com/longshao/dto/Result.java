package com.longshao.dto;

public class Result {
    public boolean success=false;
    public Object content;

    public Result() {

    }

    public Result(boolean success, Object content) {
        super();
        this.success = success;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getContent() {
        return content;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
