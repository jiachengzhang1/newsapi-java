package com.jiachengzhang.newsapi.models;

import com.jiachengzhang.newsapi.exceptions.*;
import lombok.Getter;

@Getter
public class ResError {
    private String status;
    private String code;
    private String message;
    private int statusCode;

    public ResError setStatusCode (int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public void checkError () {
        switch (code) {
            case ("apiKeyDisabled"):
                throw new APIKeyDisabledException(message, statusCode);
            case ("apiKeyExhausted"):
                throw new APIKeyExhaustedException(message, statusCode);
            case ("apiKeyInvalid"):
                throw new APIKeyInvalidException(message, statusCode);
            case ("apiKeyMissing"):
                throw new APIKeyMissingException(message, statusCode);
            case ("parameterInvalid"):
                throw new ParameterInvalidException(message, statusCode);
            case ("parametersMissing"):
                throw new ParametersMissingException(message, statusCode);
            case ("rateLimited"):
                throw new RateLimitedException(message, statusCode);
            case ("sourcesTooMany"):
                throw new SourcesTooManyException(message, statusCode);
            case ("sourceDoesNotExist"):
                throw new SourceDoesNotExistException(message, statusCode);
            case ("unexpectedError"):
                throw new UnexpectedErrorException(message, statusCode);
            default:
                throw new UnexpectedErrorException(message, -1);
        }
    }
}
