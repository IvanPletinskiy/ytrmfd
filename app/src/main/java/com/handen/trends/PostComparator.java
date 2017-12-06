package com.handen.trends;

import com.handen.trends.data.Post;

import java.util.Comparator;

/**
 * Created by Vanya on 05.12.2017.
 */

public class PostComparator implements Comparator<Post> {
    private float averagePopularity;
    private float averagePeriod;
    @Override
    public int compare(Post post1, Post post2) {
        //TODO можно оптимизировать
        float post1Value;
        float post2Value;
        if((post1.getPopularity() / averagePopularity) > (post1.getPeriod() / averagePeriod)) {
            post1Value = post1.getPopularity() / averagePopularity;
            post1.setPositive(true);
        }
        else {
            post1Value = post1.getPeriod() / averagePeriod;
            post1.setPositive(false);
        }

        if((post2.getPopularity() / averagePopularity) > (post2.getPeriod() / averagePeriod)) {
            post2Value = post2.getPopularity() / averagePopularity;
            post2.setPositive(true);
        }
        else {
            post2Value = post2.getPeriod() / averagePeriod;
            post2.setPositive(false);
        }

        if(post1Value > post2Value)
            return 1;
        else
            if (post2Value > post1Value)
                return -1;
            else
                return 0;
    }

    public PostComparator(float averagePopularity, float averagePeriod) {
        this.averagePopularity = averagePopularity;
        this.averagePeriod = averagePeriod;
    }
}
