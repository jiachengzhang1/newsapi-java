package com.newsapi.utils;

public enum AuthTypes {

    API_KEY_PARAM(""),
    X_API_KEY_HEADER("X-API-KEY"),
    AUTHORIZATION_HEADER("Authorization");

    private final String value;

    AuthTypes (String value) {
        this.value = value;
    }

    public String getValue () {
        return value;
    }
}
