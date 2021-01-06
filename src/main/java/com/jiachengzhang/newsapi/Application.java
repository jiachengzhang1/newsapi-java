package com.jiachengzhang.newsapi;

import com.jiachengzhang.newsapi.models.Sources;
import com.jiachengzhang.newsapi.params.EverythingParams;
import com.jiachengzhang.newsapi.params.SourcesParams;

import java.io.IOException;
import java.util.Map;

public class Application {


    public static void main (String[] args) throws IOException, InterruptedException {
        Map<String, String> sourcesParams = SourcesParams.newBuilder()
                .setCountry("us")
                .setLanguage("en")
                .build();

        Map<String, String> everythingParams = EverythingParams.newBuilder()
                .setPageSize(100)
                .setSearchQuery("spacex")
                .build();

        System.out.println(sourcesParams);

        String key = "6100e6fa7c484391a97f1f28e97bf95a";

        NewsAPIClient newsAPIClient = NewsAPIClient.newBuilder()
                .setApiKey(key)
                .build();

        NewsAPIResponse newsAPIResponse = newsAPIClient.getSources(sourcesParams);
        Sources sources = newsAPIResponse.getResponseBody();

//        NewsAPIResponse newsAPIResponse = newsAPIClient.getEverything(everythingParams);
//        Everything everything = newsAPIResponse.getResponseBody();


        System.out.println(newsAPIResponse.getResponseBodyAsJson());
//        System.out.println(everything.count());
        System.out.println(sources.getSources().get(0).getDescription());
    }
}
