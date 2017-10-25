package com.handen.trends.data;

import java.util.Date;

/**
 * Created by Vanya on 15.10.2017.
 */

public class User {

    int region;
    long id;
    String nickName;
    Date registrationDate;

    public User(int region, long id, String nickName, Date registrationDate) {
        this.region = region;
        this.id = id;
        this.nickName = nickName;
        this.registrationDate = registrationDate;
    }

    public int getRegion() {
        return region;
    }
}
