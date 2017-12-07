package com.handen.trends;

import com.handen.trends.data.Post;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Vanya on 05.12.2017.
 */

public class PostComparator implements Comparator<Post> {
    private float averagePopularity;
    private float averageHours;
    @Override
    public int compare(Post post1, Post post2) {
        //TODO можно оптимизировать
        long currentMillis = new Date().getTime();
        float post1Value;
        float post2Value;
        int returnValue = 0;
        System.err.println(Float.toString(averageHours));
        if((post1.getPopularity() / averagePopularity) > (averageHours / ((float)(currentMillis - post1.getPeriod()) / 3600000))) {


    //        post1Value = post1.getPopularity() / averagePopularity;

            post1Value = new BigDecimal(post1.getPopularity() / averagePopularity).setScale(9, RoundingMode.UP).floatValue();

            post1.setPositive(true);

        }
        else {
            //post1Value = (currentMillis - post1.getPeriod() / 3600000) / averageHours;

       //     averageHours / post;
       //     averageHours / (float) ((currentMillis - post1.getPeriod()) / 3600000)

            post1Value = new BigDecimal(averageHours / ((float)(currentMillis - post1.getPeriod()) / 3600000)).setScale(9, RoundingMode.UP).floatValue();

            post1.setPositive(false);
        }

        if((post2.getPopularity() / averagePopularity) > (averageHours / ((float)(currentMillis - post2.getPeriod()) / 3600000))) {
  //          post2Value = post2.getPopularity() / averagePopularity;
            post2Value = new BigDecimal(post2.getPopularity() / averagePopularity).setScale(9, RoundingMode.UP).floatValue();
            post2.setPositive(true);
        }
        else {
    //        post2Value = (currentMillis - post2.getPeriod() / 3600000) / averageHours;
            post2Value = new BigDecimal(averageHours / ((float)(currentMillis - post2.getPeriod()) / 3600000)).setScale(9, RoundingMode.UP).floatValue();
            post2.setPositive(false);
        }
/*
        if(post1Value > post2Value)
            returnValue = 1;
        if (post2Value > post1Value)
            returnValue =  -1;
        if(post1Value == post2Value)
            returnValue = 0;

        return returnValue;
        */

        if(post1Value > post2Value)
            return 1;
        else
            if (post2Value > post1Value)
                return -1;
            else
                return 0;


    }

    public PostComparator(float averagePopularity, float averageHours) {
        this.averagePopularity = averagePopularity;
        this.averageHours = averageHours;
    }
}
