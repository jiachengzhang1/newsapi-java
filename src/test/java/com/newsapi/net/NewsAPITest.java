package com.newsapi.net;

import com.newsapi.exceptions.APIKeyInvalidException;
import com.newsapi.exceptions.APIKeyMissingException;
import com.newsapi.models.Everything;
import com.newsapi.models.Sources;
import com.newsapi.models.TopHeadlines;
import com.newsapi.params.EverythingParams;
import com.newsapi.params.SourcesParams;
import com.newsapi.params.TopHeadlinesParams;
import com.newsapi.utils.AuthTypes;
import com.newsapi.utils.Endpoints;
import com.newsapi.utils.HttpVersions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class NewsAPITest {

    private NewsAPI.Builder builder;
    private String fakeApiKey;
    private String apiKey;

    @BeforeEach
    void init () {
        builder = NewsAPI.newClientBuilder();
        fakeApiKey = "123456";
        apiKey = System.getenv("NEWS_API_KEY");
    }

    @Nested
    class NewClientBuilderTest {
        @Test
        void newClientBuilderDefault () {
            NewsAPIClient newsAPIClient = builder.setApiKey(fakeApiKey).build();
            assertEquals(fakeApiKey, newsAPIClient.getApiKey());
            assertEquals(HttpVersions.HTTP, newsAPIClient.getHttpVersion());
            assertEquals(AuthTypes.AUTHORIZATION_HEADER, newsAPIClient.getAuthType());
            assertEquals(5, newsAPIClient.getTimeoutSeconds());
            assertFalse(newsAPIClient.getNoCache());
        }

        @Test
        void newClientBuilderModifiedSettings () {
            int timeoutSeconds = 10;
            HttpVersions httpVersions = HttpVersions.HTTP_2;
            NewsAPIClient newsAPIClient = builder
                    .setApiKey(fakeApiKey)
                    .setHttpVersion(httpVersions)
                    .setNoCache(true)
                    .setTimeoutSeconds(timeoutSeconds)
                    .build();

            assertEquals(httpVersions, newsAPIClient.getHttpVersion());
            assertEquals(timeoutSeconds, newsAPIClient.getTimeoutSeconds());
            assertTrue(newsAPIClient.getNoCache());
        }

        @Test
        void newClientBuilderParamAuth () {
            AuthTypes authType = AuthTypes.API_KEY_PARAM;
            NewsAPIClient newsAPIClient = builder.setAuthorization(authType, fakeApiKey).build();
            assertEquals(fakeApiKey, newsAPIClient.getApiKey());
            assertEquals(authType, newsAPIClient.getAuthType());
        }

        @Test
        void newClientBuilderXHeaderAuth () {
            AuthTypes authType = AuthTypes.X_API_KEY_HEADER;
            NewsAPIClient newsAPIClient = builder.setAuthorization(authType, fakeApiKey).build();
            assertEquals(fakeApiKey, newsAPIClient.getApiKey());
            assertEquals(authType, newsAPIClient.getAuthType());
        }

        @Test
        void newClientBuilderApiKeyMissing () {
            assertThrows(APIKeyMissingException.class, () -> builder.build());
        }
    }

    @Nested
    class makeApiCalls {
        @Test
        void getSources () throws IOException, InterruptedException {
            Map<String, String> params = SourcesParams.newBuilder()
                    .setCountry("us")
                    .build();
            NewsAPIResponse response = builder.setApiKey(apiKey).build().getSources(params);
            Sources sources = response.getBody();
            assertEquals(200, response.getStatusCode());
            assertTrue(response.getBodyAsJson().isJsonObject());
            assertEquals("ok", sources.getStatus());
            assertNotEquals(0, sources.count());
        }

        @Test
        void getEverything () throws IOException, InterruptedException {
            int pageSize = 1;
            Map<String, String> params = EverythingParams.newBuilder()
                    .setSearchQuery("spacex")
                    .setPageSize(pageSize)
                    .build();
            NewsAPIResponse response = builder.setApiKey(apiKey).build().getEverything(params);
            Everything everything = response.getBody();
            assertEquals(200, response.getStatusCode());
            assertTrue(response.getBodyAsJson().isJsonObject());
            assertEquals("ok", everything.getStatus());
            assertEquals(pageSize, everything.count());
        }

        @Test
        void getTopHeadlines () throws IOException, InterruptedException {
            int pageSize = 1;
            Map<String, String> params = TopHeadlinesParams.newBuilder()
                    .setCountry("us")
                    .setPageSize(pageSize)
                    .build();
            NewsAPIResponse response = builder.setApiKey(apiKey).build().getTopHeadlines(params);
            TopHeadlines topHeadlines = response.getBody();
            assertEquals(200, response.getStatusCode());
            assertTrue(response.getBodyAsJson().isJsonObject());
            assertEquals("ok", topHeadlines.getStatus());
            assertEquals(pageSize, topHeadlines.count());
        }

        @Test
        void get () throws IOException, InterruptedException {
            int pageSize = 1;
            Map<String, String> params = TopHeadlinesParams.newBuilder()
                    .setCountry("us")
                    .setPageSize(pageSize)
                    .build();
            NewsAPIResponse response = builder.setApiKey(apiKey).build().get(Endpoints.TOP_HEADLINES, params);
            TopHeadlines topHeadlines = response.getBody();
            assertEquals(200, response.getStatusCode());
            assertTrue(response.getBodyAsJson().isJsonObject());
            assertEquals("ok", topHeadlines.getStatus());
            assertEquals(pageSize, topHeadlines.count());

            params = EverythingParams.newBuilder()
                    .setSearchQuery("spacex")
                    .setPageSize(pageSize)
                    .build();
            response = builder.setApiKey(apiKey).build().get(Endpoints.EVERYTHING, params);
            Everything everything = response.getBody();
            assertEquals(200, response.getStatusCode());
            assertTrue(response.getBodyAsJson().isJsonObject());
            assertEquals("ok", everything.getStatus());
            assertEquals(pageSize, everything.count());

            params = SourcesParams.newBuilder()
                    .setCountry("us")
                    .build();
            response = builder.setApiKey(apiKey).build().get(Endpoints.SOURCES, params);
            Sources sources = response.getBody();
            assertEquals(200, response.getStatusCode());
            assertTrue(response.getBodyAsJson().isJsonObject());
            assertEquals("ok", sources.getStatus());
            assertNotEquals(0, sources.count());
        }
    }

    @Nested
    class makeApiCallsWithInvalidKeyTest {
        @Test
        void getSources () {
            Map<String, String> params = SourcesParams.newBuilder().setCountry("us").build();
            assertThrows(APIKeyInvalidException.class, () -> builder.setApiKey(fakeApiKey).build().getSources(params));
        }

        @Test
        void getEverything () {
            Map<String, String> params = EverythingParams.newBuilder().setSearchQuery("spacex").build();
            assertThrows(APIKeyInvalidException.class, () -> builder.setApiKey(fakeApiKey).build().getEverything(params));
        }

        @Test
        void getTopHeadlines () {
            Map<String, String> params = TopHeadlinesParams.newBuilder().setSearchQuery("spacex").build();
            assertThrows(APIKeyInvalidException.class, () -> builder.setApiKey(fakeApiKey).build().getTopHeadlines(params));
        }
    }
}