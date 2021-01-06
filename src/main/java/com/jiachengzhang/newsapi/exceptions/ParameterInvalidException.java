package com.jiachengzhang.newsapi.exceptions;

public class ParameterInvalidException extends BadRequestException {
    public ParameterInvalidException (String errorMessage, int errorCode) {
        super(errorMessage, errorCode);
    }

    public ParameterInvalidException (String errorMessage) {
        super(errorMessage);
    }
}
