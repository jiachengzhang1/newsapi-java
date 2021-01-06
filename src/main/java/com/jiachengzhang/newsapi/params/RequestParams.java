package com.jiachengzhang.newsapi.params;

import com.jiachengzhang.newsapi.utils.Options;

public class RequestParams {

    public static boolean countryInvalid (String country) {
        return !Options.countryOptions.contains(country);
    }

    public static boolean categoryInvalid (String category) {
        return !Options.categoryOptions.contains(category);
    }

    public static boolean languageInvalid (String language) {
        return !Options.languageOptions.contains(language);
    }

    public static boolean sortByInvalid (String sortBy) {
        return !Options.sortByOptions.contains(sortBy);
    }

    public static boolean pageSizeInvalid (int pageSize) {
        return pageSize <= 0 || pageSize > 100;
    }

    public static boolean searchQueryInvalid (String q) {
        return false;
    }

    public static boolean searchQueryInTitleInvalid (String qInTitle) {
        return false;
    }

    public static boolean sourcesInvalid (String sources) {
        return false;
    }

    public static boolean domainsInvalid (String domains) {
        return false;
    }

    public static boolean excludeDomainsInvalid (String excludeDomains) {
        return false;
    }

    public static boolean dateInvalid (String date) {
        return false;
    }
}
