package com.jzhangdeveloper.newsapi.exceptions;

public class ParametersMissingException extends BadRequestException {
    public ParametersMissingException (String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }
}
