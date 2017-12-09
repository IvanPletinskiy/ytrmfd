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
    private double weight;



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
   //     Date date = new Date();
        this.postDate = new Date(1512625948473L  + (getLikes() * 10000));
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

    public long getId() {
        return id;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(userId);
        dest.writeString(title);
        dest.writeString(text);
        dest.writeStringList(tags);
        dest.writeLong(views);
        dest.writeLong(likes);
        dest.writeLong(postDate.getTime());
        dest.writeByte((byte) (is24hours ? 1 : 0));
        dest.writeByte((byte) (isPositive ? 1 : 0));
        dest.writeDouble(weight);
    }

    protected Post(Parcel in) {
        id = in.readLong();
        userId = in.readLong();
        title = in.readString();
        text = in.readString();
        tags = in.createStringArrayList();
        views = in.readLong();
        likes = in.readLong();
        postDate = new Date(in.readLong());
        is24hours = in.readByte() != 0;
        isPositive = in.readByte() != 0;
        weight = in.readDouble();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public float getPopularity() {
        // (b + (l * 3 + v) * v/(l + 1))
        //int bonus = POST_BONUS;
        float is24Bonus = (is24hours)? POST_IS24BONUS : 1;

        return (POST_BONUS + (likes * 3 + views) * ((float)views / (likes + 1)) * is24Bonus);
    }

    public void setWeight(double numerator, double denominator) {
        BigDecimal bigDecimal = new BigDecimal(numerator / denominator).setScale(15, RoundingMode.UP);
        this.weight = bigDecimal.floatValue();
    }

    public double getWeight() {
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
