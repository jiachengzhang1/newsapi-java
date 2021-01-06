package com.jiachengzhang.newsapi.exceptions;

// 400
public class BadRequestException extends RuntimeException{
    private int errorCode;

    public BadRequestException(String errorMessage) {
        super(errorMessage);
    }

    public BadRequestException(final String message, final int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
        this.errorCode = -1;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
