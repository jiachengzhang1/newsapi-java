package com.jzhangdeveloper.newsapi.utils;

import java.net.http.HttpClient;

public enum HttpVersions {
    HTTP(HttpClient.Version.HTTP_1_1),
    HTTP_2(HttpClient.Version.HTTP_2);

    private final HttpClient.Version value;

    HttpVersions (HttpClient.Version value) {
        this.value = value;
    }

    public HttpClient.Version getValue () {
        return value;
    }
}
