package ru.onalex.news.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Article{
    public Source source;
    public String author;
    public String title;
    public String description;
    public String url;
    @JsonProperty("urlToImage")
    public String imgPath;
    @JsonProperty("publishedAt")
    public Date newsDate;
    public String content;
}