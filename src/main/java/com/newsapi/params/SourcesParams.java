package com.newsapi.params;

import com.newsapi.exceptions.ParameterInvalidException;
import lombok.Getter;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SourcesParams extends RequestParams {
    @NonNull
    public static Builder newBuilder () {
        return new Builder() {

            private final Map<String, String> params = new HashMap<>();

            @Override
            public Builder setCategory (String category) {
                if (categoryInvalid(category)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"category\" is invalid");
                }
                params.put("category", category);
                return this;
            }

            @Override
            public Builder setLanguage (String language) {
                if (languageInvalid(language)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"language\" is invalid");
                }
                params.put("language", language);
                return this;
            }

            @Override
            public Builder setCountry (String country) {
                if (countryInvalid(country)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"country\" is invalid");
                }
                params.put("country", country);
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
        Builder setCategory (String category);

        Builder setLanguage (String language);

        Builder setCountry (String country);

        Builder setApiKey (String apiKey);

        Map<String, String> build ();
    }
}
