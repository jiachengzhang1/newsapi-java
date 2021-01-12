package com.jzhangdeveloper.newsapi.net;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface NewsAPIResponse {
    /**
     * Gets the status code of the HTTP response.
     *
     * @return the status code of the response,
     * 200 (OK), 400 (Bad Request), 401 (Unauthorized),
     * 429 (Too Many Requests), 500 (Server Error)
     */
    int getStatusCode ();

    /**
     * Gets the headers of the HTTP response.
     *
     * @return the headers of the response
     */
    Map<String, List<String>> getHeaders ();

    /**
     * Gets the response body, the return type is generic.
     * Type of the body is different depending on the endpoint.
     * For "sources" endpoint, it returns the "Sources" type,
     * for "everything" endpoint, it returns the "Everything" type,
     * for "top headlines" endpoint, it returns the "TopHeadlines" type.
     *
     * @return the response body
     */
    <T> T getBody ();

    /**
     * Gets the response body as JsonObject
     *
     * @return the response body as Gson's JsonObject
     */
    JsonObject getBodyAsJson ();

    /**
     * Gets the response body as string.
     *
     * @return the response body of the response as string
     */
    String getBodyAsString ();
}
