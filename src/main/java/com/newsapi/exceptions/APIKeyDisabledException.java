package com.newsapi.exceptions;

public class APIKeyDisabledException extends UnauthorizedException {
    public APIKeyDisabledException (String message, int errorCode) {
        super(message, errorCode);
    }
}
