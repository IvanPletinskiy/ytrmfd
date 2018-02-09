package com.handen.trends.data;

import com.handen.trends.ClientInterface;

import java.util.Date;

/**
 * Created by Vanya on 15.10.2017.
 */

public class Comment {
    private long userId;
    private long postId;
    private String userNickname;
    private Date date;
    private int likes;
    private String text;
    private int id;

    public Comment(String text, Date date, int id, int postId, int userId) {
        this.text = text;
        this.date = date;
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        userNickname = ClientInterface.getUser(userId).getNickName();
    }

    public long getPostId() {
        return postId;
    }

    public String getText() {
        return text;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public Date getDate() {
        return date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public long getUserId() {
        return userId;
    }
}
