package com.jzhangdeveloper.newsapi.exceptions;

public class APIKeyMissingException extends UnauthorizedException {
    public APIKeyMissingException (String message, int errorCode) {
        super(message, errorCode);
    }
}
