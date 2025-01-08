package com.ll.sbbByRest.exceptions;

public class ServiceException extends RuntimeException {

    private final String resultCode;

    private final String msg;

    public ServiceException(String resultCode, String msg) {
        super(resultCode + " : " + msg);
        this.resultCode = resultCode;
        this.msg = msg;
    }



}
