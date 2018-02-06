package com.handen.trends;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;

import com.handen.trends.adapters.CategoriesListAdapter;
import com.handen.trends.data.Category;

import java.util.ArrayList;
import java.util.Arrays;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;

import static com.handen.trends.ClientInterface.getCategories;
import static com.handen.trends.ClientInterface.writePost;
import static com.handen.trends.fragments.HomeFragment.RESULT_CODE_WRITTEN;

public class WritePostActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText textEditText;
    private EditText tagsEditText;
 //   private EditText categoryEditText;
    private Switch is24hoursSwitch;

    private SearchableSpinner spinner;

    private Category choosenCategory;

    private ImageButton backImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        ActionBar actionBar = getActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayShowHomeEnabled(false); //не показываем иконку приложения
            actionBar.setDisplayShowTitleEnabled(false); // и заголовок тоже прячем
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.action_bar_write_post_activity);
        }
        else {
            getSupportActionBar().setDisplayShowHomeEnabled(false); //не показываем иконку приложения
            getSupportActionBar().setDisplayShowTitleEnabled(false); // и заголовок тоже прячем
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setCustomView(R.layout.action_bar_write_post_activity);
        }

        titleEditText = (EditText) findViewById(R.id.edit_text_title);
        textEditText = (EditText) findViewById(R.id.edit_text_post_text);

        spinner = (SearchableSpinner) findViewById(R.id.category_spinner);
        CategoriesListAdapter adapter = new CategoriesListAdapter(getCategories());
        spinner.setAdapter(adapter);

        tagsEditText = (EditText) findViewById(R.id.edit_text_tags);
        is24hoursSwitch = (Switch) findViewById(R.id.switch_is24hours);

        backImageButton = (ImageButton) findViewById(R.id.image_button_arrow_back);
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 22.10 сделать проверку, что поля ввода не пусты, иначе если пусты, не показывать AD
                showExitAlertDialog();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_write_post);
        fab.setOnClickListener(view -> {

            String title = titleEditText.getText().toString();
            Category category = (Category) spinner.getSelectedItem();

            String text = textEditText.getText().toString();

            String tagsLine = tagsEditText.getText().toString();
            String[] tagsArray = tagsLine.split(",");

            ArrayList<String> tags = new ArrayList<String>(Arrays.asList(tagsArray));

            boolean is24hours = is24hoursSwitch.isChecked();

            writePost(title, category, text, tags, is24hours);
            setResult(RESULT_CODE_WRITTEN);
            finish();
        });
    }

    private void showExitAlertDialog() {
        new AlertDialog.Builder(WritePostActivity.this)
                .setTitle(R.string.confirmation)
                .setMessage(R.string.ifYouExit)
                .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                finish();
                            }
                        }
                )
                .setNegativeButton(R.string.cancel, null)
                .create().show();
    }
}
