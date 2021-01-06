package com.jiachengzhang.newsapi.models;

import com.jiachengzhang.newsapi.exceptions.*;
import lombok.Getter;

@Getter
public class ResError {
    private int status;
    private String code;
    private String message;

    public void checkError () {
        switch (code) {
            case ("apiKeyDisabled"):
                throw new APIKeyDisabledException(message, status);
            case ("apiKeyExhausted"):
                throw new APIKeyExhaustedException(message, status);
            case ("apiKeyInvalid"):
                throw new APIKeyInvalidException(message, status);
            case ("apiKeyMissing"):
                throw new APIKeyMissingException(message, status);
            case ("parameterInvalid"):
                throw new ParameterInvalidException(message, status);
            case ("parametersMissing"):
                throw new ParametersMissingException(message, status);
            case ("rateLimited"):
                throw new RateLimitedException(message, status);
            case ("sourcesTooMany"):
                throw new SourcesTooManyException(message, status);
            case ("sourceDoesNotExist"):
                throw new SourceDoesNotExistException(message, status);
            case ("unexpectedError"):
                throw new UnexpectedErrorException(message, status);
            default:
                throw new UnexpectedErrorException(message, -1);
        }
    }
}
