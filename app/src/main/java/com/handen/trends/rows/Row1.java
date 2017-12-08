package com.handen.trends.rows;

import java.util.ArrayList;

/**
 * Created by Vanya on 05.12.2017.
 */

public class Row1 extends Row {

    int id = 1;
    int cells = 6;
    ArrayList<Integer> indexes = new ArrayList<>();

    @Override
    public ArrayList<Integer> getIndexes() {
        return indexes;
    }

    public Row1(int i) {
        indexes.add(i);
    }
    @Override
    public int getId() {
        return id;
    }
}
