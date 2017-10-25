package com.handen.trends;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.handen.trends.data.Post;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {

    public static final String ARGS_POSTS = "posts";
    public static final String ARGS_POST_POSITION = "position";

    private ViewPager mViewPager;
    private ImageButton mBackButton;
    private TextView mPostTitleTextView;
    private ArrayList<Post> posts;
    private int postPosition;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ActionBar actionBar = getActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayShowHomeEnabled(false); //не показываем иконку приложения
            actionBar.setDisplayShowTitleEnabled(false); // и заголовок тоже прячем
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.action_bar_post_activity);
        }
        else {
            getSupportActionBar().setDisplayShowHomeEnabled(false); //не показываем иконку приложения
            getSupportActionBar().setDisplayShowTitleEnabled(false); // и заголовок тоже прячем
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setCustomView(R.layout.action_bar_post_activity);
        }

        mViewPager = (ViewPager) findViewById(R.id.view_pager_activity_post);

        mBackButton = (ImageButton) findViewById(R.id.image_button_arrow_back);
        mPostTitleTextView = (TextView) findViewById(R.id.text_view_post_title);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        postPosition = getIntent().getIntExtra(ARGS_POST_POSITION, 0);
        posts = (ArrayList<Post>) getIntent().getSerializableExtra(ARGS_POSTS);


        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {

                mPostTitleTextView.setText(posts.get(position).getTitle());

                return PostFragment.newInstance(posts.get(position));
            }

            @Override
            public int getCount() {
                return posts.size();
            }
        });

        mViewPager.setCurrentItem(postPosition);

    }

}
