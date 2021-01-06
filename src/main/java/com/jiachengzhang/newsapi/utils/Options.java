package com.jiachengzhang.newsapi.utils;

import java.util.HashSet;
import java.util.Set;

public final class Options {
    public static final Set<String> sortByOptions = new HashSet<>() {{
        add("relevancy");
        add("popularity");
        add("publishedAt");
    }};

    public static final Set<String> categoryOptions = new HashSet<>() {{
        add("business");
        add("entertainment");
        add("general");
        add("health");
        add("science");
        add("sports");
        add("technology");
    }};

    public static final Set<String> languageOptions = new HashSet<>() {{
        add("ar");
        add("de");
        add("en");
        add("es");
        add("fr");
        add("he");
        add("it");
        add("nl");
        add("no");
        add("pt");
        add("ru");
        add("se");
        add("ud");
        add("zh");
    }};

    public static final Set<String> countryOptions = new HashSet<>() {{
        add("ae");
        add("ar");
        add("at");
        add("au");
        add("be");
        add("bg");
        add("br");
        add("ca");
        add("ch");
        add("cn");
        add("co");
        add("cu");
        add("cz");
        add("de");
        add("eg");
        add("fr");
        add("gb");
        add("gr");
        add("hk");
        add("hu");
        add("id");
        add("ie");
        add("il");
        add("in");
        add("it");
        add("jp");
        add("kr");
        add("lt");
        add("lv");
        add("ma");
        add("mx");
        add("my");
        add("ng");
        add("nl");
        add("no");
        add("nz");
        add("ph");
        add("pl");
        add("pt");
        add("ro");
        add("rs");
        add("ru");
        add("sa");
        add("se");
        add("sg");
        add("si");
        add("sk");
        add("th");
        add("tr");
        add("tw");
        add("ua");
        add("us");
        add("ve");
        add("za");
    }};
}
