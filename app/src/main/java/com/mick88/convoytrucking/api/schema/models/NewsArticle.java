package com.mick88.convoytrucking.api.schema.models;

/**
 * Created by Michal on 09/06/2016.
 */
public class NewsArticle {
    int id;
    String date;
    String title;
    String content;

    @Override
    public String toString() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
