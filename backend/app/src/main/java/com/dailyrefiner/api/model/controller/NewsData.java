package com.dailyrefiner.api.model.controller;

public class NewsData {

    public String url;
    public String title;
    public String type;

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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public NewsData(String url, String title, String type) {
        this.url = url;
        this.title = title;
        this.type = type;
    }
}
