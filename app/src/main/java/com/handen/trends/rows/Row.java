package com.handen.trends.rows;

import java.util.ArrayList;

/**
 * Created by Vanya on 05.12.2017.
 */

public abstract class Row {
    int id = 0;

    ArrayList<Integer> indexes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getIndexes() {
        return indexes;
    }
}
