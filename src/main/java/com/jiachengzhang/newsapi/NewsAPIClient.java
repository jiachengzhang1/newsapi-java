package com.jiachengzhang.newsapi;

import com.google.gson.Gson;
import com.jiachengzhang.newsapi.exceptions.APIKeyMissingException;
import com.jiachengzhang.newsapi.models.Everything;
import com.jiachengzhang.newsapi.models.ResError;
import com.jiachengzhang.newsapi.models.Sources;
import com.jiachengzhang.newsapi.models.TopHeadlines;
import com.jiachengzhang.newsapi.utils.AuthTypes;
import com.jiachengzhang.newsapi.utils.Endpoints;
import com.jiachengzhang.newsapi.utils.HttpVersions;
import lombok.NonNull;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

import static com.jiachengzhang.newsapi.utils.AuthTypes.AUTHORIZATION_HEADER;

public abstract class NewsAPIClient {
    protected HttpClient httpClient;

    @NonNull
    static Builder newBuilder () {
        return new Builder() {

            private HttpVersions httpVersion = HttpVersions.HTTP;
            private int timeoutSeconds = 5;
            private AuthTypes authType = AUTHORIZATION_HEADER; // default authorization method
            private String apiKey = "";

            @Override
            public Builder setTimeoutSeconds (int seconds) {
                this.timeoutSeconds = seconds;
                return this;
            }

            @Override
            public Builder setHttpVersion (HttpVersions httpVersion) {
                this.httpVersion = httpVersion;
                return this;
            }

            @Override
            public Builder setApiKey (String apiKey) {
                this.apiKey = apiKey;
                return this;
            }

            @Override
            public Builder setAuthorization (AuthTypes authType, String apiKey) {
                this.authType = authType;
                return setApiKey(apiKey);
            }

            @Override
            public NewsAPIClient build () {
                if (apiKey.isEmpty()) {
                    throw new APIKeyMissingException("API Key is Missing", 401);
                }

                NewsAPIClient newsAPIClient = new NewsAPIClient() {

                    @Override
                    @NonNull
                    NewsAPIResponse getSources (Map<String, String> params)
                            throws IOException, InterruptedException {
                        HttpResponse<String> httpResponse = makeNewsAPIRequest(params, Endpoints.SOURCES);
                        return new NewsAPIResponse(httpResponse) {
                            @Override
                            @SuppressWarnings("unchecked")
                            public Sources getResponseBody () {
                                return new Gson().fromJson(getHttpResponse().body(), Sources.class);
                            }
                        };
                    }

                    @Override
                    NewsAPIResponse get (String endpoint, Map<String, String> params)
                            throws IOException, InterruptedException {
                        return null;
                    }

                    @Override
                    @NonNull
                    NewsAPIResponse getEverything (Map<String, String> params)
                            throws IOException, InterruptedException {
                        HttpResponse<String> httpResponse = makeNewsAPIRequest(params, Endpoints.EVERYTHING);
                        return new NewsAPIResponse(httpResponse) {
                            @Override
                            @SuppressWarnings("unchecked")
                            public Everything getResponseBody () {
                                return new Gson().fromJson(getHttpResponse().body(), Everything.class);
                            }
                        };
                    }

                    @Override
                    @NonNull
                    NewsAPIResponse getTopHeadlines (Map<String, String> params)
                            throws IOException, InterruptedException {
                        HttpResponse<String> httpResponse = makeNewsAPIRequest(params, Endpoints.TOP_HEADLINES);
                        return new NewsAPIResponse(httpResponse) {
                            @Override
                            @SuppressWarnings("unchecked")
                            public TopHeadlines getResponseBody () {
                                return new Gson().fromJson(getHttpResponse().body(), TopHeadlines.class);
                            }
                        };
                    }

                    private HttpResponse<String> makeNewsAPIRequest (Map<String, String> params, Endpoints endpoint)
                            throws IOException, InterruptedException {
                        HttpRequest httpRequest = NewsAPIRequest.newBuilder()
                                .setRequestParams(params)
                                .setRequestAuthHeader(authType, apiKey)
                                .setEndPoint(endpoint)
                                .build().getHttpRequest();
                        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                        checkError(response);
                        return response;
                    }

                    private void checkError (@NonNull HttpResponse<String> response) {
                        int statusCode = response.statusCode();
                        if (statusCode == 200) {
                            return;
                        }
                        ResError error = new Gson().fromJson(response.body(), ResError.class);
                        error.checkError();
                    }
                };

                newsAPIClient.httpClient = HttpClient.newBuilder()
                        .version(httpVersion.getValue())
                        .connectTimeout(Duration.ofSeconds(timeoutSeconds))
                        .build();
                return newsAPIClient;
            }
        };
    }

    abstract NewsAPIResponse getSources (Map<String, String> params) throws IOException, InterruptedException;

    abstract NewsAPIResponse get (String endpoint, Map<String, String> params) throws IOException, InterruptedException;

    abstract NewsAPIResponse getEverything (Map<String, String> params) throws IOException, InterruptedException;

    abstract NewsAPIResponse getTopHeadlines (Map<String, String> params) throws IOException, InterruptedException;

    interface Builder {
        Builder setTimeoutSeconds (int seconds);

        Builder setHttpVersion (HttpVersions httpVersion);

        Builder setApiKey (String apiKey);

        Builder setAuthorization (AuthTypes authType, String apiKey);

        NewsAPIClient build ();
    }
}

