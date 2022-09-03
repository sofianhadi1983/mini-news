package com.alterra.miniproject.mininews.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class NewsApiArticle {
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String content;
    private OffsetDateTime publishedAt;

    public void setSource(NewsApiSource source) {
        this.source = source.getName();
    }
}
