package com.handen.trends;

import com.handen.trends.data.Post;

import java.util.Comparator;

/**
 * Created by Vanya on 05.12.2017.
 */

public class PostComparator implements Comparator<Post> {

    @Override
    public int compare(Post post1, Post post2) {
        double post1Weight = post1.getWeight();
        double post2Weight = post2.getWeight();
        //Если первый меньше второго, возвращаем -1, иначе 0, иначе 1
        if (post1Weight > post2Weight) {
            return -1;
        }
        else {
            if (post2Weight > post1Weight) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }
}
