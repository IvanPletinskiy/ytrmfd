package com.handen.trends;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.handen.trends.data.Post;
import com.handen.trends.data.User;

import java.util.ArrayList;

import static com.handen.trends.ClientInterface.getUser;
import static com.handen.trends.ClientInterface.getUserPosts;


public class UserProfileActivity extends AppCompatActivity {

    private static final String ARGS_USER_ID = "userId";

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageButton backImageButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private long userId;
    private ArrayList<Post> userPosts = new ArrayList<>();
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
//        //TODO 11.11.2017 сделать как в quiz, разобраться как правильно
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_activity_user_profile);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        userId = getIntent().getLongExtra(ARGS_USER_ID, -1);
        userPosts = getUserPosts(userId);
        user = getUser(userId);

        viewPager = (ViewPager) findViewById(R.id.view_pager_activity_user_profile);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));

        tabLayout = (TabLayout) findViewById(R.id.tab_layout_activity_user_profile);
        tabLayout.setupWithViewPager(viewPager);

        backImageButton = (ImageButton) findViewById(R.id.image_button_arrow_back);
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(
                R.id.collapsing_toolbar_activity_user_profile);
        collapsingToolbarLayout.setTitle(user.getNickName());

    }

    private long getTotalLikes() {
        long totalLikes = 0;

        for(Post post : userPosts) {
            totalLikes += post.getLikes();
        }

        return totalLikes;
    }

    class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return TilesFragment.newInstance(userPosts);
                case 1:
                    return UserAboutFragment.newInstance(user.getRegistrationDate(),
                            0, userPosts.size(), getTotalLikes());

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
                //TODO 12.11.2017 replace with string resources
                case 0:return "Тренды";
                case 1:return "О пользователе";
                default: return null;
            }

        }
    }

}
