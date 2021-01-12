package com.jzhangdeveloper.newsapi.exceptions;

public class SourceDoesNotExistException extends BadRequestException {
    public SourceDoesNotExistException (String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }
}
