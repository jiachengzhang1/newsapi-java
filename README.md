# newsapi-java

A flexible and easy-to-use Java wrapper for [News API](https://newsapi.org). (It only supports for Java 11 and above at
the moment)

[![Unit Test](https://github.com/jiachengzhang1/newsapi-java/workflows/Unit%20Tests/badge.svg)](https://github.com/jiachengzhang1/newsapi-java)
[![Maven](https://img.shields.io/maven-central/v/com.jzhangdeveloper.newsapi/newsapi-java.svg?label=Maven)](https://search.maven.org/search?q=g:%22com.jzhangdeveloper.newsapi%22%20AND%20a:%22newsapi-java%22)


## Installation

### Maven
Add the dependency to the `pom.xml` file
```xml
<dependency>
  <groupId>com.jzhangdeveloper.newsapi</groupId>
  <artifactId>newsapi-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Quick Start

**First, create a News API client**

```java
NewsAPIClient client = NewsAPI.newClientBuilder()
        .setApiKey(key)
        .build();
```

**Then, build request parameters**

```java
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
NewAPIResponse response = client.getSources(sourcesParams);

// get status code
response.getStatusCode()

// get response body as a Java object and Json object (use Gson)
Sources sources = response.getBody();
JsonObject sourcesJson = response.getBodyAsJson();

// get headers
Map<String, List<String>>headers = response.getHeaders();
```

## Documentation
To build query parameters, `TopHeadlinesParams.newBuilder()`, `EverythingParams.newBuilder()` and `SourcesParams.newBuilder()` are avaliable to use. There are setters for each builder to help with creating proper params for a specific endpoint. Alternatively, you can build one your own with `Map<String, String>` type. Read [News API Documentation](https://newsapi.org/docs/endpoints) for proper naming.

`NewsAPI.newClientBuilder()`: Initiate a `NewsAPI.Builder` that configures the Http request, settings are following,
- `setApiKey(String apiKey)`: Set api key for News API, this setting uses Bearer authorization header
- `setAuthorization (AuthTypes authType, String apiKey)`: Set api key and authorization type, options are `AuthTypes.API_KEY_PARAM` (pass api key as a query param), `AuthTypes.AUTHORIZATION_HEADER` (use Bearer authorization header) and `AuthTypes.X_API_KEY_HEADER` (use X-API-KEY header)
- `setHttpVersion (HttpVersions httpVersion)`: Set the http version, `HttpVersions.HTTP` (HTTP 1.1) or `HttpVersions.HTTP_2` (HTTP 2)
- `setNoCache (boolean noCache)`: No cache on News API when it's set to `true`
- `build()`: Build a `NewsAPIClient` instance

`NewsAPIClient`: It's an interface, an instance can be created through `NewsAPI.newClientBuilder().build()`.
- `get (Endpoints endpoint, Map<String, String> params)`: Make a `GET` request to the `endpoint` passed in, query params are `params`
- `getEverything (Map<String, String> params)`: `GET /everything`, query params are `params`
- `getTopHeadlines (Map<String, String> params)`: `GET /top-headlines`, query params are `params`
- `getSources (Map<String, String> params)`: `GET /sources`, query params are `params`

`NewsAPIResponse`: It's an interface, an instance is returned after making an api call, get the response details by,
- `getStatusCode()`: Get response status code
- `getBodyAsString()`: Get response body as `String`
- `getBodyAsJson()`: Get response body as a JSON object
- `getBody()`: Get response body as a Java object, `Everything`, `TopHeadlines` or `Sources` depending on the endpoint
- `getHeaders()`: Get response headers as a Map

**Exceptions are throwed if the response status code is not `2xx`**

See Javadoc for more details.
