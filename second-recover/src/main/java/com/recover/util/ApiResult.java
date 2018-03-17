package com.recover.util;

public class ApiResult<T> {
    /**
     *  1:成功，-1：失败，0：需要登录
     */
    public int code;

    public String message;

    private T data;

    public ApiResult(){

    }

    public ApiResult(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
