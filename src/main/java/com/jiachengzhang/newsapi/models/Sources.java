package com.jiachengzhang.newsapi.models;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class Sources {
    @Getter private List<Source> sources;

    public Sources() {
        sources = new LinkedList<>();
    }

    public void add(Source source) {
        sources.add(source);
    }

    public int count() {
        return sources.size();
    }

    public JsonObject toJson() {
        return null;
    }
}
