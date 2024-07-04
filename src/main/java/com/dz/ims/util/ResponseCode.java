package com.dz.ims.util;

public enum ResponseCode {

    SUCCESS(200),

    NO_CONTENT(204),

    PROCCESSING_FAIL(500);

    private final int code;

    ResponseCode(int code){
        this.code = code;
    }

    public int value(){
        return  code;
    }
}
