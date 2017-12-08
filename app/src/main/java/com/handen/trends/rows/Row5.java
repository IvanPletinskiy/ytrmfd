package com.handen.trends.rows;

import java.util.ArrayList;

/**
 * Created by Vanya on 05.12.2017.
 */

public class Row5 extends Row {
    int id = 5;
    int cells = 2;

    ArrayList<Integer> indexes = new ArrayList<>();

    @Override
    public ArrayList<Integer> getIndexes() {
        return indexes;
    }

    public Row5(int i, int i1, int i2) {
        indexes.add(i);
        indexes.add(i1);
        indexes.add(i2);
    }
    @Override
    public int getId() {
        return id;
    }
}
