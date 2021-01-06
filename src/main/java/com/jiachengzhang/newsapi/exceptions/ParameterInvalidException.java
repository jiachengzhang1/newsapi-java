package com.jiachengzhang.newsapi.exceptions;

public class ParameterInvalidException extends BadRequestException {
    public ParameterInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
