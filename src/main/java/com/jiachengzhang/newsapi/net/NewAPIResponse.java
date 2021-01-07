package com.jiachengzhang.newsapi.net;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public interface NewAPIResponse {
    int getStatusCode ();

    Map<String, List<String>> getHeaders ();

    <T> T getBody ();

    JsonObject getBodyAsJson ();

    String getBodyAsString ();
}
