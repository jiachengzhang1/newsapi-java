package com.jzhangdeveloper.newsapi.net;

import com.jzhangdeveloper.newsapi.utils.AuthTypes;
import com.jzhangdeveloper.newsapi.utils.Endpoints;
import com.jzhangdeveloper.newsapi.utils.HttpVersions;

import java.io.IOException;
import java.util.Map;

/**
 *
 */
public interface NewsAPIClient {

    /**
     * @param params
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    NewsAPIResponse getSources (Map<String, String> params) throws IOException, InterruptedException;

    /**
     * @param endpoint
     * @param params
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    NewsAPIResponse get (Endpoints endpoint, Map<String, String> params) throws IOException, InterruptedException;

    /**
     * @param params
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    NewsAPIResponse getEverything (Map<String, String> params) throws IOException, InterruptedException;

    /**
     * @param params
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    NewsAPIResponse getTopHeadlines (Map<String, String> params) throws IOException, InterruptedException;

    boolean getNoCache ();

    int getTimeoutSeconds ();

    HttpVersions getHttpVersion ();

    String getApiKey ();

    AuthTypes getAuthType ();
}
