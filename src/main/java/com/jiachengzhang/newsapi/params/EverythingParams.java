package com.jiachengzhang.newsapi.params;

import com.jiachengzhang.newsapi.exceptions.ParameterInvalidException;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class EverythingParams extends RequestParams {
    @NonNull
    public static Builder newBuilder () {
        return new Builder() {

            private final Map<String, String> params = new HashMap<>();

            @Override
            public Builder setSources (String sources) {
                params.put("sources", sources);
                return this;
            }

            @Override
            public Builder setDomains (String domains) {
                if (domainsInvalid(domains)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"domains\" is invalid");
                }
                params.put("domains", domains);
                return this;
            }

            @Override
            public Builder setExcludeDomains (String excludeDomains) {
                if (excludeDomainsInvalid(excludeDomains)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"exclude domains\" is invalid");
                }
                params.put("excludeDomains", excludeDomains);
                return this;
            }

            @Override
            public Builder setFrom (String from) {
                if (dateInvalid(from)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"from\" is invalid");
                }
                params.put("from", from);
                return this;
            }

            @Override
            public Builder setTo (String to) {
                if (dateInvalid(to)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"to\" is invalid");
                }
                params.put("to", to);
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
            public Builder setSortBy (String sortBy) {
                if (sortByInvalid(sortBy)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"sort by\" is invalid");
                }
                params.put("sortBy", sortBy);
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
            public Builder setSearchQueryInTitle (String qInTitle) {
                if (searchQueryInTitleInvalid(qInTitle)) {
                    throw new ParameterInvalidException("Invalid Parameters: \"search query in title\" is invalid");
                }
                params.put("qInTitle", qInTitle);
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
        Builder setSearchQuery (String q);

        Builder setSearchQueryInTitle (String qInTitle);

        Builder setSources (String sources);

        Builder setDomains (String domains);

        Builder setExcludeDomains (String excludeDomains);

        Builder setFrom (String from);

        Builder setTo (String to);

        Builder setLanguage (String language);

        Builder setSortBy (String sortBy);

        Builder setPageSize (int pageSize);

        Builder setPage (int page);

        Builder setApiKey (String apiKey);

        Map<String, String> build ();
    }
}
