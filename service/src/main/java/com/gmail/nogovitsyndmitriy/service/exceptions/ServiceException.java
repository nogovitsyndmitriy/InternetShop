package com.gmail.nogovitsyndmitriy.service.exceptions;

public class ServiceException extends RuntimeException {
    private String errCode;
    private String errMsg;

    public ServiceException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
