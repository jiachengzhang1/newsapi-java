package com.newsapi.net;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newsapi.models.ResError;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

/**
 *
 */
abstract class NewsAPIHttpResponse implements NewsAPIResponse {
    private final HttpResponse<String> httpResponse;

    public NewsAPIHttpResponse (HttpResponse<String> httpResponse) {
        checkResponseError(httpResponse);
        this.httpResponse = httpResponse;
    }

    @Override
    public int getStatusCode () {
        return httpResponse.statusCode();
    }

    @Override
    public Map<String, List<String>> getHeaders () {
        return httpResponse.headers().map();
    }

    @Override
    public abstract <T> T getBody ();

    @Override
    public JsonObject getBodyAsJson () {
        String body = getBodyAsString();
        return new Gson().fromJson(body, JsonObject.class);
    }

    @Override
    public String getBodyAsString () {
        return httpResponse.body();
    }

    private void checkResponseError (HttpResponse<String> httpResponse) {
        int statusCode = httpResponse.statusCode();
        if (statusCode != 200) {
            System.out.println(httpResponse.body());
            new Gson().fromJson(httpResponse.body(), ResError.class).setStatusCode(statusCode).checkError();
        }
    }
}
