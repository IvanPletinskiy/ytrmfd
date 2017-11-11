package com.handen.trends;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import static com.handen.trends.ClientInterface.getPosts;
import static com.handen.trends.ClientInterface.getSubscribedPosts;


public class UserProfileActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
//        //TODO 11.11.2017 сделать как в quiz, разобраться как правильно
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_user_profile);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.view_pager_activity_user_profile);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_activity_user_profile);
        tabLayout.setupWithViewPager(viewPager);

    }

    class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return TilesFragment.newInstance(getPosts(0));
                case 1:
                    return TilesFragment.newInstance(getSubscribedPosts());
                case 2:
                    return TilesFragment.newInstance(getPosts(2));
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                //
                //Your tab titles
                //
                case 0:return "Мир";
                case 1:return "Подписки";
                default: return null;
            }

        }
    }

}
