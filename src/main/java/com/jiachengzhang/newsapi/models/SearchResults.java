package com.jiachengzhang.newsapi.models;

import lombok.Getter;

import java.util.List;

@Getter
public class SearchResults {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public int count () {
        return articles.size();
    }
}
