package com.jiachengzhang.newsapi.exceptions;

public class APIKeyMissingException extends UnauthorizedException {
    public APIKeyMissingException (String message, int errorCode) {
        super(message, errorCode);
    }
}
