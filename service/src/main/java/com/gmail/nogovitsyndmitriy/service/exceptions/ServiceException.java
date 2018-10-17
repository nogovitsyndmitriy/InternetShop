package com.gmail.nogovitsyndmitriy.service.exceptions;

public class ServiceException extends RuntimeException {
    private String errMsg;

    public ServiceException(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
