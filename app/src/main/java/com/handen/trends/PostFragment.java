package com.handen.trends;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handen.trends.data.Post;

import java.io.Serializable;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {

    private static final String ARGS_POST = "post";

    private Post post;

    private TextView nicknameTextView;
    private TextView dateTextView;
    private TextView postTextTextView;
    private TextView likesTextView;
    private TextView viewTextView;
    private LinearLayout userDescriptionLinearLayout;


    public PostFragment() {
        // Required empty public constructor
    }


    public static PostFragment newInstance(Post post) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_POST, (Serializable) post);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            post = (Post) getArguments().getSerializable(ARGS_POST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post, container, false);

        nicknameTextView = (TextView) view.findViewById(R.id.text_view_nickname);
        nicknameTextView.setText(post.getUserNickname());

        dateTextView = (TextView) view.findViewById(R.id._text_view_date);
        dateTextView.setText("32 окт 13:13");
        postTextTextView = (TextView) view.findViewById(R.id.text_view_post_text);
        postTextTextView.setText(post.getText());

        likesTextView = (TextView) view.findViewById(R.id.text_view_likes);
        likesTextView.setText(Long.toString(post.getLikes()));
        viewTextView = (TextView) view.findViewById(R.id.text_view_views);
        viewTextView.setText(Long.toString(post.getViews()));

        userDescriptionLinearLayout = (LinearLayout) view.findViewById(R.id.linear_layout_user_description);
        userDescriptionLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
