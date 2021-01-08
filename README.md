# newsapi-java

A flexible and easy-to-use Java wrapper for [News API](https://newsapi.org). (It only supports for Java 11 and above at the moment)

## Installation
### Maven

### Gradle

## Usage
**First, create a News API client**
```java
import com.newsapi.net.NewsAPI;
import com.newsapi.net.NewsAPIClient;

NewsAPIClient client = NewsAPI.newClientBuilder()
        .setApiKey(key)
        .build();
```

**Then, build request parameters**
```java
import com.newsapi.params.TopHeadlinesParams;
import com.newsapi.params.EverythingParams;
import com.newsapi.params.SourcesParams;

// an example for "Top Headlines" endpoint
Map<String, String> topHeadlineParams = TopHeadlinesParams.newBuilder()
                .setCountry("us")
                .setPageSize(10)
                ...
                .build();

// an example for "Everything" endpoint
Map<String, String> everythingParams = EverythingParams.newBuilder()
                .setPageSize(100)
                .setSearchQuery("spacex")
                ...
                .build();

// an example for "Source" endpoint
Map<String, String> sourcesParams = SourcesParams.newBuilder()
                .setCountry("us")
                .setLanguage("en")
                ...
                .build();
```

**Finally, get recourses**
```java
import com.newsapi.net.NewAPIResponse;
import com.newsapi.models.Sources;
NewAPIResponse response = client.getSources(sourcesParams);

// get status code
response.getStatusCode()

// get response body as a Java object and Json object (use Gson)
Sources sources = response.getBody();
JsonObject sourcesJson = response.getBodyAsJson();

// get headers
Map<String, List<String>> headers = response.getHeaders();
```

## Documentation


