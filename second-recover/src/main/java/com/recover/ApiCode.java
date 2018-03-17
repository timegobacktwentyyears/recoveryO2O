package com.recover;

public enum ApiCode {
    SUCCESS(1),FAIL(-1),UN_SIGN(0);

    private int code;

    ApiCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
}
