package com.handen.trends.rows;

import java.util.ArrayList;

/**
 * Created by Vanya on 08.12.2017.
 */

public class Row7 extends Row {

    int id = 7;
    int cells = 3;

    ArrayList<Integer> indexes = new ArrayList<>();

    @Override
    public ArrayList<Integer> getIndexes() {
        return indexes;
    }

    public Row7(int i) {
        indexes.add(i);
    }
    @Override
    public int getId() {
        return id;
    }
}
