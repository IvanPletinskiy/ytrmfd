package com.handen.trends.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handen.trends.ClientInterface;
import com.handen.trends.R;

public class LikedFragment extends NavigationFragment {
    public static final String TAG_LIKED = "liked";

    public LikedFragment() {
        // Required empty public constructor
    }
    public static LikedFragment newInstance() {
        return new LikedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_liked, container, false);
        findViews(view);
        return view;
    }

    @Override
    public void findViews(View view) {
        super.findViews(view);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.liked);

        TilesFragment tilesFragment = TilesFragment.newInstance(ClientInterface.getLiked());
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_host, tilesFragment)
                .commit();
    }
}
