package com.handen.trends;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.handen.trends.data.Post;
import com.handen.trends.fragments.PostFragment;

import java.util.ArrayList;

import static com.handen.trends.EditPostActivity.ARGS_POST;

public class PostActivity extends AppCompatActivity implements PostFragment.SetPostTitleInterface {
    public static final String ARGS_POSTS = "posts";
    public static final String ARGS_POST_POSITION = "position";

    public static final int REQUEST_CODE = 1;
    public static final int RESULT_CODE_EDITED = 11;
    public static final int RESULT_CODE_DELETED = 12;
    public static final int RESULT_CODE_BACK = 10;

    private ViewPager mViewPager;
    private ImageButton mBackButton;
    private TextView mPostTitleTextView;
    private ArrayList<Post> posts;
    private int postPosition;
    private FragmentStatePagerAdapter fragmentStatePagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
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


        fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PostFragment.newInstance(posts.get(position));
            }

            @Override
            public int getCount() {
                return posts.size();
            }
        };
        mViewPager.setAdapter(fragmentStatePagerAdapter);

        mViewPager.setCurrentItem(postPosition);
    }

    @Override
    public void setTitle(String postTitle) {
        mPostTitleTextView.setText(postTitle);
    }

    @Override
    public void startEditionActivity(Post post) {
        Intent intent = new Intent(this, EditPostActivity.class);
        intent.putExtra(ARGS_POST, post);
        //intent.putExtra(ARGS_TITLE, post.getTitle());
        //intent.putExtra(ARGS_TEXT, post.getText());
        //intent.putExtra(ARGS_CATEGORY, post.getCategory());
        startActivityForResult(intent, posts.indexOf(post));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_CODE_BACK:
                System.out.println("BACK");
                break;
            case RESULT_CODE_DELETED:
                posts.remove(requestCode);
                fragmentStatePagerAdapter.notifyDataSetChanged();
                System.out.println("DELETED");

                break;
            case RESULT_CODE_EDITED:
                fragmentStatePagerAdapter.notifyDataSetChanged();
                System.out.println("EDITED");
                break;
        }
    }
}