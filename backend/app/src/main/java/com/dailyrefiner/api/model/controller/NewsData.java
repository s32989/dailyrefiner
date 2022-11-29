package com.dailyrefiner.api.model.controller;

public class NewsData {

    public String url;
    public String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NewsData(String url, String title) {
        this.url = url;
        this.title = title;
    }
}
