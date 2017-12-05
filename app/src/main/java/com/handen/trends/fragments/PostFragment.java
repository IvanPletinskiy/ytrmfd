package com.handen.trends.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handen.trends.R;
import com.handen.trends.data.Post;
import com.handen.trends.userActivity.UserProfileActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {

    private static final String ARGS_POST = "post";
    private static final String ARGS_USER_ID = "userId";

    private Post post;
    private Long userId;

    private TextView nicknameTextView;
    private TextView dateTextView;
    private TextView postTextTextView;
    private TextView likesTextView;
    private TextView viewTextView;
    private LinearLayout userDescriptionLinearLayout;

    SetPostTitleInterface mListener;


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
            userId = post.getUserId();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post, container, false);

        mListener.setTitle(post.getTitle());

        nicknameTextView = (TextView) view.findViewById(R.id.text_view_nickname);
        nicknameTextView.setText(post.getUserNickname());

        dateTextView = (TextView) view.findViewById(R.id._text_view_date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        dateTextView.setText(dateFormat.format(post.getPostDate()));
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
                intent.putExtra(ARGS_USER_ID, userId);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof SetPostTitleInterface) {
            mListener = (SetPostTitleInterface) context;
        }
    }

    public interface SetPostTitleInterface {
        void setTitle(String postTitle);

    }

}
