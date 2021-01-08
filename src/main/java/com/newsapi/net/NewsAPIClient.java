package com.newsapi.net;

import java.io.IOException;
import java.util.Map;

public interface NewsAPIClient {
    NewAPIResponse getSources (Map<String, String> params) throws IOException, InterruptedException;

    NewAPIResponse get (String endpoint, Map<String, String> params) throws IOException, InterruptedException;

    NewAPIResponse getEverything (Map<String, String> params) throws IOException, InterruptedException;

    NewAPIResponse getTopHeadlines (Map<String, String> params) throws IOException, InterruptedException;
}
