package com.handen.trends.data;

import java.io.Serializable;

/**
 * Created by Vanya on 21.10.2017.
 */

public class Category implements Serializable{

    private String title;

    public Category(String title) {
        this.title = title;
    }

    public String getName() {
        return title;
    }
}
