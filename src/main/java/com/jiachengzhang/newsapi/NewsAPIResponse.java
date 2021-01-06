package com.jiachengzhang.newsapi;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public abstract class NewsAPIResponse {

    private final HttpResponse<String> httpResponse;

    public NewsAPIResponse(HttpResponse<String> httpResponse) {
        this.httpResponse = httpResponse;
    }

    public int getStatusCode() {
        return httpResponse.statusCode();
    }

    public Map<String, List<String>> getHeaders() {
        HttpHeaders httpHeaders = httpResponse.headers();
        return null;
    }

    public abstract <T> T getResponseBody();
}
