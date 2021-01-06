package com.jiachengzhang.newsapi.exceptions;

public class APIKeyExhaustedException extends UnauthorizedException {
    public APIKeyExhaustedException(String message, int errorCode) {
        super(message, errorCode);
    }
}
