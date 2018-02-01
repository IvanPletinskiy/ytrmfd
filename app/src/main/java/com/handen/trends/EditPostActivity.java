package com.handen.trends;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.handen.trends.data.Post;

import static com.handen.trends.PostActivity.RESULT_CODE_BACK;
import static com.handen.trends.PostActivity.RESULT_CODE_DELETED;
import static com.handen.trends.PostActivity.RESULT_CODE_EDITED;

public class EditPostActivity extends AppCompatActivity {

    public static String ARGS_TITLE = "title";
    public static String ARGS_TEXT = "text";
    public static String ARGS_CATEGORY = "category";
    public static String ARGS_TAGS = "tags";
    public static String ARGS_POST = "post";

    private Post post;

 //   private String title;
 //   private String text;
 //   private Category category;
 //   private ArrayList<String> tags;

    private ImageButton backImageButton;
    private ImageButton deleteButton;
    private EditText titleEditText;
    private EditText textEditText;
    private FloatingActionButton doneFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
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

        post = (Post) args.get(ARGS_POST);

  //      title = args.getString(ARGS_TITLE);
  //      text = args.getString(ARGS_TEXT);
  //      category = (Category) args.getSerializable(ARGS_CATEGORY);

        backImageButton = (ImageButton) findViewById(R.id.image_button_arrow_back);
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditPostActivity.this)
                        .setTitle(R.string.confirmation)
                        .setMessage(R.string.ifYouExit)
                        .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                setResult(RESULT_CODE_BACK);
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .create().show();
            }
        });

        deleteButton = (ImageButton) findViewById(R.id.delete_image_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditPostActivity.this)
                        .setTitle(R.string.deleting)
                        .setMessage(R.string.doYouWontToDeleteTrend)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ClientInterface.deletePost(post.getId());
                                setResult(RESULT_CODE_DELETED);
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.no, null)
                        .create().show();
            }
        });
        titleEditText = (EditText) findViewById(R.id.title_edit_text);
        titleEditText.setText(post.getTitle());

        textEditText = (EditText) findViewById(R.id.post_text_edit_text);
        textEditText.setText(post.getText());

        doneFab = (FloatingActionButton) findViewById(R.id.fab_done);
        doneFab.setOnClickListener(v -> {
            setResult(RESULT_CODE_EDITED);
            long postId = post.getId();
            String title = titleEditText.getText().toString();
            String text = textEditText.getText().toString();
            ClientInterface.updatePost(postId, title, text);
            finish();
        });
    }
}
