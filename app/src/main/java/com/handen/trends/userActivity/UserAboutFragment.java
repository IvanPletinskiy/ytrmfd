package com.handen.trends.userActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.handen.trends.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Vanya on 12.11.2017
 */

public class UserAboutFragment extends Fragment {

    private static final String ARGS_DESCRIPTION = "description";
    private static final String ARGS_DATE = "date";
    private static final String ARGS_SUBSCRIBERS = "subscribers";
    private static final String ARGS_POSTS = "posts";
    private static final String ARGS_LIKES = "likes";

    private TextView userDescriptionTextView;
    private TextView registrationDateTextView;
    private TextView totalSubscribersTextView;
    private TextView totalPostsTextView;
    private TextView totalLikesTextView;

    private String userDescription;
    private Date registrationDate;
    private long totalSubscribers;
    private long totalPosts;
    private long totalLikes;

    private OnFragmentInteractionListener mListener;

    public UserAboutFragment() {

    }


    public static UserAboutFragment newInstance(String userDescription, Date registrationDate, long totalSubscribers,
                                                long totalPosts, long totalLikes) {
        UserAboutFragment fragment = new UserAboutFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_DESCRIPTION, userDescription);
        args.putSerializable(ARGS_DATE, registrationDate);
        args.putLong(ARGS_SUBSCRIBERS, totalSubscribers);
        args.putLong(ARGS_POSTS, totalPosts);
        args.putLong(ARGS_LIKES, totalLikes);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle args = getArguments();

            userDescription = args.getString(ARGS_DESCRIPTION);
            registrationDate = (Date) args.getSerializable(ARGS_DATE);
            totalSubscribers = args.getLong(ARGS_SUBSCRIBERS, -1);
            totalPosts = args.getLong(ARGS_POSTS, -1);
            totalLikes = args.getLong(ARGS_LIKES, -1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_about, container, false);

        userDescriptionTextView = (TextView) view.findViewById(R.id.text_view_user_description);
        userDescriptionTextView.setText(userDescription);

        registrationDateTextView = (TextView) view.findViewById(R.id.text_view_registration_date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        registrationDateTextView.setText( dateFormat.format(registrationDate));

        totalSubscribersTextView = (TextView) view.findViewById(R.id.text_view_total_subscribers);
        totalSubscribersTextView.setText(Long.toString(totalSubscribers));

        totalPostsTextView = (TextView) view.findViewById(R.id.text_view_total_posts);
        totalPostsTextView.setText(Long.toString(totalPosts));

        totalLikesTextView = (TextView) view.findViewById(R.id.text_view_total_likes);
        totalLikesTextView.setText(Long.toString(totalLikes));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
