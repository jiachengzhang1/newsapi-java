package com.jiachengzhang.newsapi.exceptions;

public class APIKeyInvalidException extends UnauthorizedException {
    public APIKeyInvalidException(String message, int errorCode) {
        super(message, errorCode);
    }
}
