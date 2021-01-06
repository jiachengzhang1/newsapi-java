package com.jiachengzhang.newsapi.exceptions;

// 429
public class RateLimitedException extends RuntimeException {
    public RateLimitedException(String errorMessage) {
        super(errorMessage);
    }
}