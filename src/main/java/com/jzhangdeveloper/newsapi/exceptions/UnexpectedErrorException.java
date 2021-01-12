package com.jzhangdeveloper.newsapi.exceptions;

public class UnexpectedErrorException extends RuntimeException {
    private int errorCode;

    public UnexpectedErrorException (String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode () {
        return errorCode;
    }
}
