package com.handen.trends.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Vanya on 25.11.2017.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    public TabAdapter(ArrayList<Fragment> fragments, ArrayList<String> titles, FragmentManager fm) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);

/*        switch (position) {
            case 0:
                return TilesFragment.newInstance(getPosts(0));
            case 1:
                return TilesFragment.newInstance(getSubscribedPosts());
            case 2:
                return TilesFragment.newInstance(getPosts(2));
        }
        return null;
        */
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return titles.get(position);

/*        switch (position) {


            //TODO replace with string resources
            case 0:
                return "Мир";
            case 1:
                return "Подписки";
            case 2:
                return "Беларусь";
            default:
                return null;
        }
        */

    }
}
