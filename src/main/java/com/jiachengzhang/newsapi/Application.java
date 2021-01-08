package com.jiachengzhang.newsapi;

import com.jiachengzhang.newsapi.models.Sources;
import com.jiachengzhang.newsapi.net.NewAPIResponse;
import com.jiachengzhang.newsapi.net.NewsAPI;
import com.jiachengzhang.newsapi.net.NewsAPIClient;
import com.jiachengzhang.newsapi.params.EverythingParams;
import com.jiachengzhang.newsapi.params.SourcesParams;
import com.jiachengzhang.newsapi.utils.HttpVersions;

import java.io.IOException;
import java.util.Map;

public class Application {


    public static void main (String[] args) throws IOException, InterruptedException {
        String key = "";


        Map<String, String> sourcesParams = SourcesParams.newBuilder()
                .setCountry("us")
                .setLanguage("en")
                .build();

        NewsAPIClient client = NewsAPI.newClientBuilder()
                .setApiKey(key)
                .setTimeoutSeconds(5)
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
