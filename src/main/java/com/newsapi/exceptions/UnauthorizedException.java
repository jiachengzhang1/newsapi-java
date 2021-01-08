package com.newsapi.exceptions;

// 401
public class UnauthorizedException extends RuntimeException {
    private int errorCode;

    public UnauthorizedException (String errorMessage) {
        super(errorMessage);
    }

    public UnauthorizedException (final String message, final int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public UnauthorizedException (final String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = -1;
    }

    public int getErrorCode () {
        return errorCode;
    }
}
