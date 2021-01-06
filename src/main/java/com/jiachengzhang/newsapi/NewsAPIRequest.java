package com.jiachengzhang.newsapi;

import com.jiachengzhang.newsapi.exceptions.APIKeyMissingException;
import com.jiachengzhang.newsapi.utils.AuthTypes;
import com.jiachengzhang.newsapi.utils.Endpoints;
import lombok.val;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.jiachengzhang.newsapi.utils.AuthTypes.API_KEY_PARAM;
import static com.jiachengzhang.newsapi.utils.AuthTypes.AUTHORIZATION_HEADER;

public class NewsAPIRequest {

    private static final String BASE_URL = "https://newsapi.org/v2/";
    private static final String CONTENT_TYPE = "application/json";

    private HttpRequest httpRequest;

    static Builder newBuilder() {
        return new Builder() {
            private AuthTypes authType = AUTHORIZATION_HEADER; // default authorization method
            private String apiKey = "";
            private Endpoints endpoint;
            private Map<String, String> params;

            @Override
            public Builder setApiKey(String apiKey) {
                this.apiKey = apiKey;
                return this;
            }

            @Override
            public Builder setRequestAuthHeader(AuthTypes authType, String apiKey) {
                this.authType = authType;
                return setApiKey(apiKey);
            }

            @Override
            public Builder setEndPoint(Endpoints endPoint) {
                this.endpoint = endPoint;
                return this;
            }

            @Override
            public Builder setRequestParams(Map<String, String> params) {
                this.params = params;
                return this;
            }

            @Override
            public NewsAPIRequest build() {
                if (apiKey.isEmpty())
                    throw new APIKeyMissingException("API Key is Missing", 401);

                NewsAPIRequest newsAPIRequest = new NewsAPIRequest();
                String uri = buildUri(endpoint.getValue(), buildParamsString());
                HttpRequest.Builder httpRequestBuilder = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(uri))
                        .setHeader("Content-Type", CONTENT_TYPE);

                if (authType != API_KEY_PARAM) {
                    String token = authType == AUTHORIZATION_HEADER ? String.format("Bearer %s", apiKey) : apiKey;
                    httpRequestBuilder.setHeader(authType.getValue(), token);
                }
                newsAPIRequest.httpRequest = httpRequestBuilder.build();
                return newsAPIRequest;
            }

            private String buildParamsString() {
                // add api key into the params if the user asked for it
                if (authType == API_KEY_PARAM && !params.containsKey("apiKey")) {
                    params.put("apiKey", apiKey);
                }

                val paramsEntrySet = params.entrySet();

                if (paramsEntrySet.isEmpty())
                    return "";

                StringBuilder stringBuilder = new StringBuilder("?");
                for (var entry : paramsEntrySet) {
                    stringBuilder.append(String.format("%s=%s&", encode(entry.getKey()), encode(entry.getValue())));
                }
                return stringBuilder.toString();
            }

            private String encode(String str) {
                return URLEncoder.encode(str, StandardCharsets.UTF_8);
            }

            private String buildUri(String endPoint, String paramString) {
                return String.format("%s/%s%s", BASE_URL, endPoint, paramString);
            }
        };
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    interface Builder {
        Builder setApiKey(String apiKey);

        Builder setRequestAuthHeader(AuthTypes authType, String apiKey);

        Builder setEndPoint(Endpoints endPoint);

        Builder setRequestParams(Map<String, String> params);

        NewsAPIRequest build();
    }
}
