package com.jiachengzhang.newsapi;

import com.jiachengzhang.newsapi.utils.Options;

public class RequestParams {

    public static boolean isCountryValid(String country) {
        return Options.countryOptions.contains(country);
    }

    public static boolean isCategoryValid(String category) {
        return Options.categoryOptions.contains(category);
    }

    public static boolean isLanguageValid(String language) {
        return Options.languageOptions.contains(language);
    }

    public static boolean isSortByValid(String sortBy) {
        return Options.sortByOptions.contains(sortBy);
    }

    public static boolean isPageSizeValid(int pageSize) {
        return pageSize > 0 && pageSize <= 100;
    }

    public static boolean isSearchQueryValid(String q) {
        return true;
    }

    public static boolean isSearchQueryInTitleValid(String qInTitle) {
        return true;
    }

    public static boolean isSourcesValid(String sources) {
        return true;
    }

    public static boolean isDomainsValid(String domains) {
        return true;
    }

    public static boolean isExcludeDomainsValid(String excludeDomains) {
        return true;
    }

    public static boolean isDateValid(String date) {
        return true;
    }
}
