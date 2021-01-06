package com.jiachengzhang.newsapi.exceptions;

public class SourcesTooManyException extends BadRequestException {
    public SourcesTooManyException (String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }
}