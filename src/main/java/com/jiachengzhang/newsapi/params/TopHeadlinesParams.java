package com.jiachengzhang.newsapi.params;

import com.jiachengzhang.newsapi.exceptions.ParameterInvalidException;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class TopHeadlinesParams extends RequestParams {
    @NonNull
    public static Builder newBuilder () {
        return new Builder() {

            private final Map<String, String> params = new HashMap<>();

            @Override
            public Builder setCountry (String country) {
                if (countryInvalid(country)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"country\" is invalid");
                }
                params.put("country", country);
                return this;
            }

            @Override
            public Builder setCategory (String category) {
                if (categoryInvalid(category)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"category\" is invalid");
                }
                params.put("category", category);
                return this;
            }

            @Override
            public Builder setSources (String sources) {
                if (sourcesInvalid(sources)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"sources\" is invalid");
                }
                params.put("sources", sources);
                return this;
            }

            @Override
            public Builder setSearchQuery (String q) {
                if (searchQueryInvalid(q)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"search query\" is invalid");
                }
                params.put("q", q);
                return this;
            }

            @Override
            public Builder setPageSize (int pageSize) {
                if (pageSizeInvalid(pageSize)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"page size\" is invalid");
                }
                params.put("pageSize", Integer.toString(pageSize));
                return this;
            }

            @Override
            public Builder setPage (int page) {
                params.put("page", Integer.toString(page));
                return this;
            }

            @Override
            public Builder setApiKey (String apiKey) {
                params.put("apiKey", apiKey);
                return this;
            }

            @Override
            public Map<String, String> build () {
                return params;
            }
        };
    }

    public interface Builder {
        Builder setCountry (String country);

        Builder setCategory (String category);

        Builder setSources (String sources);

        Builder setSearchQuery (String q);

        Builder setPageSize (int pageSize);

        Builder setPage (int page);

        Builder setApiKey (String apiKey);

        Map<String, String> build ();
    }
}
