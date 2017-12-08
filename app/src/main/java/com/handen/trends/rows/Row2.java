package com.handen.trends.rows;

import java.util.ArrayList;

/**
 * Created by Vanya on 05.12.2017.
 */

public class Row2 extends Row {

    int id = 2;
    int cells = 3;

    ArrayList<Integer> indexes = new ArrayList<>();

    @Override
    public ArrayList<Integer> getIndexes() {
        return indexes;
    }

    public Row2(int i, int i1) {
        indexes.add(i);
        indexes.add(i1);
    }

    @Override
    public int getId() {
        return id;
    }
}
