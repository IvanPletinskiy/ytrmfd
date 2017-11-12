package com.handen.trends.data;

import java.util.Date;

/**
 * Created by Vanya on 15.10.2017.
 */

public class User {

    private int region;
    private long id;
    private String nickName;
    private Date registrationDate;
    private String description;

    public User(int region, long id, String nickName, Date registrationDate) {
        this.region = region;
        this.id = id;
        this.nickName = nickName;
        this.registrationDate = registrationDate;
    }

    public int getRegion() {
        return region;
    }

    public long getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
