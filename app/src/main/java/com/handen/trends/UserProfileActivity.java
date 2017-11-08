package com.handen.trends;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mmin18.widget.RealtimeBlurView;
import com.handen.trends.appbarspring.src.main.java.android.support.design.widget.AppBarLayoutSpringBehavior;
import com.handen.trends.appbarspring.src.main.java.android.support.design.widget.TabScrimHelper;

import static com.handen.trends.ClientInterface.getPosts;
import static com.handen.trends.ClientInterface.getSubscribedPosts;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        final RealtimeBlurView realtimeBlurView = (RealtimeBlurView) findViewById(R.id.real_time_blur_view);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        AppBarLayoutSpringBehavior springBehavior = (AppBarLayoutSpringBehavior) ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).getBehavior();
        springBehavior.setSpringOffsetCallback(new AppBarLayoutSpringBehavior.SpringOffsetCallback() {
            @Override
            public void springCallback(int offset) {
                int radius = 20 * (240 - offset > 0 ? 240 - offset : 0) / 240;
                realtimeBlurView.setBlurRadius(radius);
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.tabs_viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
        TabScrimHelper tabScrimHelper = new TabScrimHelper(tabLayout, collapsingToolbarLayout);
        appBarLayout.addOnOffsetChangedListener(tabScrimHelper);
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
            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                //
                //Your tab titles
                //
                case 0:return "Мир";    //TODO 08.11.2017 Перевести в строковые ресурсы
                case 1:return "Подписки";

                default: return null;
            }

        }
    }

}
