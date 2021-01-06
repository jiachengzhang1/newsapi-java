package com.jiachengzhang.newsapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.NonNull;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public abstract class NewsAPIResponse {

    private final HttpResponse<String> httpResponse;

    public NewsAPIResponse (@NonNull HttpResponse<String> httpResponse) {
        this.httpResponse = httpResponse;
    }

    public int getStatusCode () {
        return httpResponse.statusCode();
    }

    public HttpResponse<String> getHttpResponse () {
        return httpResponse;
    }

    public Map<String, List<String>> getHeaders () {
        HttpHeaders httpHeaders = httpResponse.headers();
        return null;
    }

    public abstract <T> T getResponseBody ();

    public Error getError () {
        return new Gson().fromJson(httpResponse.body(), Error.class);
    }

    public JsonObject getResponseBodyAsJson () {
        return new Gson().fromJson(httpResponse.body(), JsonObject.class);
    }
}
