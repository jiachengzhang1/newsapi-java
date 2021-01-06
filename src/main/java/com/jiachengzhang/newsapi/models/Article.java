package com.jiachengzhang.newsapi.models;

import lombok.Getter;

@Getter
public class Article {
    private String status;
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishAt;
    private String content;
}
