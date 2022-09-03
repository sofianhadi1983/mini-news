package com.alterra.miniproject.mininews.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsApiResponse {
    private String status;
    private Integer totalResults;
    private List<NewsApiArticle> articles;
}
