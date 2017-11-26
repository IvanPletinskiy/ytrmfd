package com.handen.trends.fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handen.trends.ClientInterface;
import com.handen.trends.R;

import static com.handen.trends.ClientInterface.currentUserId;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends NavigationFragment {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    public static MyProfileFragment newInstance() {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        findView(view);
        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public void findView(View view) {
        super.findView(view);
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_my_profile);
        collapsingToolbarLayout.setTitle(ClientInterface.getUser(currentUserId).getNickName());
        System.err.println("FindView");

    }
}
