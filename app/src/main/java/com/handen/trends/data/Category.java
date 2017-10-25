package com.handen.trends.data;

import java.io.Serializable;

/**
 * Created by Vanya on 21.10.2017.
 */

public class Category implements Serializable{

    private String title;
    private int id = 0;

    public Category(String title) {
        this.title = title;
    }
}
