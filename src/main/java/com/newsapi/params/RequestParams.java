package com.newsapi.params;

import com.newsapi.utils.Options;

import java.util.regex.Pattern;

public class RequestParams {

    private static final String domainsRegex = "^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$";

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


    public static boolean domainsInvalid (String domains) {
        for (String s : domains.split(",")) {
            Pattern p = Pattern.compile(domainsRegex);
            if (s == null || !p.matcher(s).matches()) {
                return false;
            }
        }
        return true;
    }

    public static boolean excludeDomainsInvalid (String excludeDomainsInvalid) {
        return domainsInvalid(excludeDomainsInvalid);
    }


    public static boolean dateInvalid (String date) {
        return false;
    }
}
