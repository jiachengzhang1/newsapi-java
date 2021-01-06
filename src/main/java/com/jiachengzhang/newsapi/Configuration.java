package com.jiachengzhang.newsapi;

import com.jiachengzhang.newsapi.utils.AuthTypes;
import com.jiachengzhang.newsapi.utils.Endpoints;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Configuration {
    private String apiKey;
    private AuthTypes authType;
    private Endpoints endpoint;

    public Configuration(String apiKey, AuthTypes authType) {
        this.apiKey = apiKey;
        this.authType = authType;
    }
}
