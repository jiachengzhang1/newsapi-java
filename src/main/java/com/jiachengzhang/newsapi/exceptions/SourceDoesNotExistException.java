package com.jiachengzhang.newsapi.exceptions;

public class SourceDoesNotExistException extends BadRequestException {
    public SourceDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
