package com.jiachengzhang.newsapi.exceptions;

public class UnexpectedErrorException extends RuntimeException {
    public UnexpectedErrorException(String errorMessage) {
        super(errorMessage);
    }
}
