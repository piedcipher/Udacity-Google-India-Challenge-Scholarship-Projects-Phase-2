package com.wordpress.piedcipher.samachar.models;

public class News {

    private String headline;
    private String thumbnail;
    private String url;
    private String timestamp;
    private String section;
    private String author;

    public News(String headline, String thumbnail, String url, String timestamp, String section, String author) {
        this.headline = headline;
        this.thumbnail = thumbnail;
        this.url = url;
        this.timestamp = timestamp;
        this.section = section;
        this.author = author;
    }

    public String getHeadline() {
        return headline;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSection() {
        return section;
    }

    public String getAuthor() {
        return author;
    }
}