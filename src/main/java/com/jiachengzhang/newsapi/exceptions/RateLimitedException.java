package com.jiachengzhang.newsapi.exceptions;

// 429
public class RateLimitedException extends RuntimeException {
    private int errorCode;

    public RateLimitedException (String errorMessage, int errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode () {
        return errorCode;
    }
}