package com.jzhangdeveloper.newsapi.exceptions;

public class APIKeyExhaustedException extends UnauthorizedException {
    public APIKeyExhaustedException (String message, int errorCode) {
        super(message, errorCode);
    }
}
