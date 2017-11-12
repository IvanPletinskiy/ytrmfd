package com.handen.trends.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vanya on 15.10.2017.
 */

public class Post implements Serializable {

    private long id;
    private long userId;
    private String title;
    private ArrayList<Category> categories;
    private String text;
    private ArrayList<String> tags;
    private long views;
    private long likes;
    private Date creationDate;
    private boolean is24hours;


    public Post(String title, ArrayList<Category> categories, String text, ArrayList<String> tags, boolean is24hours, long id, long userId, long views, long likes) {
        this.title = title;
        this.categories = categories;
        this.text = text;
        this.tags = tags;
        this.is24hours = is24hours;
        this.views = views;
        this.likes = likes;
        this.creationDate = new Date();
        this.id = id;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public long getLikes() {
        return likes;
    }

    public boolean is24hours() {
        return is24hours;
    }

    public long getViews() {
        return views;
    }

    public Post(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getUserNickname() {
        return "Handen";
    }

    public String getText() {
        return text;
    }

    public long getUserId() {
        return userId;
    }
}
