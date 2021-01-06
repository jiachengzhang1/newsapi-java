package com.jiachengzhang.newsapi.models;

import lombok.Getter;

@Getter
public class Source {
    private final String id;
    private final String name;
    private final String description;
    private final String url;
    private final String category;
    private final String language;
    private final String country;

    public Source(String id, String name, String description, String url, String category, String language, String country) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }
}
