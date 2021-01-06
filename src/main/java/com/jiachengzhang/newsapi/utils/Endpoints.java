package com.jiachengzhang.newsapi.utils;

public enum Endpoints {

    TOP_HEADLINES("top-headlines"),
    EVERYTHING("everything"),
    SOURCES("sources");

    private final String value;

    Endpoints (String value) {
        this.value = value;
    }

    public String getValue () {
        return value;
    }
}
