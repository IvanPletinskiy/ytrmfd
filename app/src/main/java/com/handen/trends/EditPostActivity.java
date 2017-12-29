package com.handen.trends;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.handen.trends.data.Category;

import java.util.ArrayList;

public class EditPostActivity extends AppCompatActivity {

    public static String ARGS_TITLE = "title";
    public static String ARGS_TEXT = "text";
    public static String ARGS_CATEGORY = "category";
    public static String ARGS_TAGS = "tags";

    private String title;
    private String text;
    private Category category;
    private ArrayList<String> tags;

    private ImageButton backImageButton;
    private ImageButton deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        ActionBar actionBar = getActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false); //не показываем иконку приложения
            actionBar.setDisplayShowTitleEnabled(false); // и заголовок тоже прячем
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.action_bar_edit_post_activity);
        }
        else {
            getSupportActionBar().setDisplayShowHomeEnabled(false); //не показываем иконку приложения
            getSupportActionBar().setDisplayShowTitleEnabled(false); // и заголовок тоже прячем
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setCustomView(R.layout.action_bar_edit_post_activity);
        }

        Intent intent = getIntent();
        Bundle args = intent.getExtras();

        title = args.getString(ARGS_TITLE);
        text = args.getString(ARGS_TEXT);
        category = (Category) args.getSerializable(ARGS_CATEGORY);

        backImageButton = (ImageButton) findViewById(R.id.image_button_arrow_back);
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setResult
                finish();
            }
        });

        deleteButton = (ImageButton) findViewById(R.id.delete_image_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setResult();
                finish();

            }
        });
    }
}
