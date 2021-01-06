package com.jiachengzhang.newsapi;

import java.io.IOException;
import java.util.Map;

import static com.jiachengzhang.newsapi.utils.AuthTypes.API_KEY_PARAM;

public class Application {


    public static void main(String[] args) throws IOException, InterruptedException {
        Map<String, String> sourcesParams = SourcesParams.newBuilder()
                .setCountry("us")
                .setLanguage("en")
                .build();

        System.out.println(sourcesParams);

        String key = "6100e6fa7c484391a97f1f28e97bf95a";

        NewsAPIClient newsAPIClient = NewsAPIClient.newBuilder()
                .setAuthorization(API_KEY_PARAM, key)
                .build();

        NewsAPIResponse newsAPIResponse = newsAPIClient.getSources(sourcesParams);

        System.out.println(newsAPIResponse.getStatusCode());
    }
}
