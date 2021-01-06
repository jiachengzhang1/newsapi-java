package com.jiachengzhang.newsapi.exceptions;

public class ParametersMissingException extends BadRequestException {
    public ParametersMissingException(String errorMessage) {
        super(errorMessage);
    }
}
