package com.jiachengzhang.newsapi.net;

import com.google.gson.Gson;
import com.jiachengzhang.newsapi.models.Everything;
import com.jiachengzhang.newsapi.models.Sources;
import com.jiachengzhang.newsapi.models.TopHeadlines;
import com.jiachengzhang.newsapi.utils.AuthTypes;
import com.jiachengzhang.newsapi.utils.Endpoints;
import com.jiachengzhang.newsapi.utils.HttpVersions;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

import static com.jiachengzhang.newsapi.utils.AuthTypes.API_KEY_PARAM;

public class NewsAPI {
    public static Builder newClientBuilder () {
        return new Builder() {

            private static final String BASE_URL = "https://newsapi.org/v2";
            private static final String CONTENT_TYPE = "application/json";

            private final HttpClient.Builder httpClientBuilder = HttpClient.newBuilder();
            private final HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder();

            private AuthTypes authType = AuthTypes.AUTHORIZATION_HEADER;
            private String apiKey;


            @Override
            public Builder setTimeoutSeconds (int seconds) {
                httpClientBuilder.connectTimeout(Duration.ofSeconds(seconds));
                return this;
            }

            @Override
            public Builder setHttpVersion (HttpVersions httpVersion) {
                httpClientBuilder.version(httpVersion.getValue());
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
                this.apiKey = apiKey;
                return this;
            }

            @Override
            public NewsAPIClient build () {
                return new NewsAPIClient() {
                    private final HttpClient httpClient = httpClientBuilder.build();

                    @Override
                    public NewAPIResponse getSources (Map<String, String> params) throws IOException, InterruptedException {
                        HttpResponse<String> httpResponse = send(buildRequest(Endpoints.SOURCES, params));
                        return new NewsAPIHttpResponse(httpResponse) {
                            @Override
                            @SuppressWarnings("unchecked")
                            public Sources getBody () {
                                return new Gson().fromJson(getBodyAsString(), Sources.class);
                            }
                        };
                    }

                    @Override
                    public NewAPIResponse get (String endpoint, Map<String, String> params) throws IOException, InterruptedException {
                        return null;
                    }

                    @Override
                    public NewAPIResponse getEverything (Map<String, String> params) throws IOException, InterruptedException {
                        HttpResponse<String> httpResponse = send(buildRequest(Endpoints.EVERYTHING, params));
                        return new NewsAPIHttpResponse(httpResponse) {
                            @Override
                            @SuppressWarnings("unchecked")
                            public Everything getBody () {
                                return new Gson().fromJson(getBodyAsString(), Everything.class);
                            }
                        };
                    }

                    @Override
                    public NewAPIResponse getTopHeadlines (Map<String, String> params) throws IOException, InterruptedException {
                        HttpResponse<String> httpResponse = send(buildRequest(Endpoints.TOP_HEADLINES, params));
                        return new NewsAPIHttpResponse(httpResponse) {
                            @Override
                            @SuppressWarnings("unchecked")
                            public TopHeadlines getBody () {
                                return new Gson().fromJson(getBodyAsString(), TopHeadlines.class);
                            }
                        };
                    }

                    private HttpResponse<String> send (HttpRequest httpRequest) throws IOException, InterruptedException {
                        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                    }
                };
            }

            private HttpRequest buildRequest (Endpoints endpoint, Map<String, String> params) {
                String uri = buildUri(endpoint.getValue(), buildParamsString(params));
                httpRequestBuilder.GET().uri(URI.create(uri)).setHeader("Content-Type", CONTENT_TYPE);
                if (authType != AuthTypes.API_KEY_PARAM) {
                    String token = authType == AuthTypes.AUTHORIZATION_HEADER ? String.format("Bearer %s", apiKey) : apiKey;
                    httpRequestBuilder.setHeader(authType.getValue(), token);
                }
                return httpRequestBuilder.build();
            }

            private String buildParamsString (Map<String, String> params) {
                // add api key into the params if the user asked for it
                if (authType == API_KEY_PARAM && !params.containsKey("apiKey")) {
                    params.put("apiKey", apiKey);
                }

                if (params.isEmpty()) {
                    return "";
                }

                StringBuilder stringBuilder = new StringBuilder("?");
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    stringBuilder.append(String.format("%s=%s&", encode(entry.getKey()), encode(entry.getValue())));
                }
                return stringBuilder.substring(0, stringBuilder.length() - 1);
            }

            private String encode (String str) {
                return URLEncoder.encode(str, StandardCharsets.UTF_8);
            }

            private String buildUri (String endPoint, String paramString) {
                return String.format("%s/%s%s", BASE_URL, endPoint, paramString);
            }
        };
    }

    public interface Builder {
        Builder setTimeoutSeconds (int seconds);

        Builder setHttpVersion (HttpVersions httpVersion);

        Builder setApiKey (String apiKey);

        Builder setAuthorization (AuthTypes authType, String apiKey);

        NewsAPIClient build ();
    }
}
