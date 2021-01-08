package com.newsapi;

import com.newsapi.models.Sources;
import com.newsapi.net.NewAPIResponse;
import com.newsapi.net.NewsAPI;
import com.newsapi.net.NewsAPIClient;
import com.newsapi.params.EverythingParams;
import com.newsapi.params.SourcesParams;
import com.newsapi.utils.HttpVersions;

import java.io.IOException;
import java.util.Map;

public class Application {


    public static void main (String[] args) throws IOException, InterruptedException {
        String key = "6100e6fa7c484391a97f1f28e97bf95a";


        Map<String, String> sourcesParams = SourcesParams.newBuilder()
                .setCountry("us")
                .setLanguage("en")
                .build();

        NewsAPIClient client = NewsAPI.newClientBuilder()
                .setApiKey(key)
                .setTimeoutSeconds(5)
                .setNoCache(true)
                .setHttpVersion(HttpVersions.HTTP)
                .build();

        NewAPIResponse response = client.getSources(sourcesParams);

        Sources sources = response.getBody();

        Map<String, String> everythingParams = EverythingParams.newBuilder()
                .setPageSize(100)
                .setSearchQuery("spacex-and mars")
                .build();


        System.out.println(sourcesParams);

        System.out.println(response.getBodyAsString());
        System.out.println(sources.getSources().get(0).getDescription());
        response.getHeaders().forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
