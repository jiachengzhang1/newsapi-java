package com.jiachengzhang.newsapi;

import com.jiachengzhang.newsapi.exceptions.APIKeyMissingException;
import com.jiachengzhang.newsapi.models.Sources;
import com.jiachengzhang.newsapi.utils.AuthTypes;
import com.jiachengzhang.newsapi.utils.Endpoints;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

import static com.jiachengzhang.newsapi.utils.AuthTypes.AUTHORIZATION_HEADER;

public abstract class NewsAPIClient {
    protected HttpClient httpClient;

    static Builder newBuilder() {
        return new Builder() {

            private AuthTypes authType = AUTHORIZATION_HEADER; // default authorization method
            private String apiKey = "";

            @Override
            public Builder setApiKey(String apiKey) {
                this.apiKey = apiKey;
                return this;
            }

            @Override
            public Builder setAuthorization(AuthTypes authType, String apiKey) {
                this.authType = authType;
                return setApiKey(apiKey);
            }

            @Override
            public NewsAPIClient build() {
                if (apiKey.isEmpty())
                    throw new APIKeyMissingException("API Key is Missing", 401);

                NewsAPIClient newsAPIClient = new NewsAPIClient() {

                    @Override
                    NewsAPIResponse getSources(Map<String, String> params) throws IOException, InterruptedException {
                        HttpRequest httpRequest = buildNewsAPIRequest(params, Endpoints.SOURCES).getHttpRequest();
                        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                        return new NewsAPIResponse(httpResponse) {
                            @Override
                            @SuppressWarnings("unchecked")
                            public Sources getResponseBody() {
                                return null;
                            }
                        };
                    }

                    @Override
                    NewsAPIResponse get(String endpoint, Map<String, String> params) {
                        return null;
                    }

                    @Override
                    NewsAPIResponse getEverything(Map<String, String> params) {
                        return null;
                    }

                    @Override
                    NewsAPIResponse getTopHeadlines(Map<String, String> params) throws IOException {
                        return null;
                    }

                    private NewsAPIRequest buildNewsAPIRequest(Map<String, String> params, Endpoints endpoint) {
                        return NewsAPIRequest.newBuilder()
                                .setRequestParams(params)
                                .setRequestAuthHeader(authType, apiKey)
                                .setEndPoint(endpoint)
                                .build();
                    }
                };

                newsAPIClient.httpClient = HttpClient.newBuilder()
                        .version(HttpClient.Version.HTTP_1_1)
                        .connectTimeout(Duration.ofSeconds(5))
                        .build();
                return newsAPIClient;
            }
        };
    }

    abstract NewsAPIResponse getSources(Map<String, String> params) throws IOException, InterruptedException;

    abstract NewsAPIResponse get(String endpoint, Map<String, String> params);

    abstract NewsAPIResponse getEverything(Map<String, String> params);

    abstract NewsAPIResponse getTopHeadlines(Map<String, String> params) throws IOException;

    interface Builder {
        Builder setApiKey(String apiKey);

        Builder setAuthorization(AuthTypes authType, String apiKey);

        NewsAPIClient build();

    }
}

