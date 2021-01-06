package com.jiachengzhang.newsapi.models;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Sources {
    private final List<Source> sources;
    private String status;

    public Sources () {
        sources = new LinkedList<>();
    }

    public int count () {
        return sources.size();
    }
}
