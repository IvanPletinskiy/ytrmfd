package com.handen.trends.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.handen.trends.ClientInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

import static com.handen.trends.ClientInterface.POST_BONUS;
import static com.handen.trends.ClientInterface.POST_IS24BONUS;

/**
 * Created by Vanya on 15.10.2017.
 */

public class Post implements Parcelable {

    private long id;
    private long userId;
    private String title;
    private ArrayList<Category> categories;
    private Category category;
    private String text;
    private ArrayList<String> tags;
    private long views;
    private long likes;
    private Date postDate;
    private boolean is24hours;


    private boolean isPositive;
    private float weight;



    public Post(String title, ArrayList<Category> categories, String text, ArrayList<String> tags, boolean is24hours, long id, long userId, long views, long likes) {
        this.title = title;
        this.categories = categories;
        this.text = text;
        this.tags = tags;
        this.is24hours = is24hours;
        this.views = views;
        this.likes = likes;
       // this.postDate = new Date();
        Date date = new Date();
        this.postDate = new Date(1512625948473L  + (getLikes() * 10000));
        this.id = id;
        this.userId = userId;
    }

    public Post(String title, Category category, String text, ArrayList<String> tags, boolean is24hours, long id, long userId) {
        this.title = title;
        this.categories = categories;
        this.text = text;
        this.tags = tags;
        this.is24hours = is24hours;
        this.views = 0;
        this.likes = 0;
       // this.postDate = new Date();
        Date date = new Date();
        this.postDate = new Date(date.getTime() + (getLikes() * 1000000));
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

    public Date getPostDate() {
        return postDate;
    }

    public String getUserNickname() {
        return ClientInterface.getUser(userId).getNickName();
    }

    public String getText() {
        return text;
    }

    public long getUserId() {
        return userId;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setPositive(boolean positive) {
        isPositive = positive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public float getPopularity() {
        // (b + (l * 3 + v) * v/(l + 1))
        //int bonus = POST_BONUS;
        float is24Bonus = (is24hours)? POST_IS24BONUS : 1;

        return (POST_BONUS + (likes * 3 + views) * ((float)views / (likes + 1)));
    }

    public void setWeight(float numerator, float denominator) {
        BigDecimal bigDecimal = new BigDecimal(numerator / denominator).setScale(15, RoundingMode.UP);
        this.weight = bigDecimal.floatValue();
    }

    public float getWeight() {
        return weight;
    }

    public long getPeriod() {
        return postDate.getTime();
    }

    @Override
    public String toString() {
        return title;
    }
}
