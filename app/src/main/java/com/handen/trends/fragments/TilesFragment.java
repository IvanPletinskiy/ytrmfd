package com.handen.trends.fragments;

import android.content.Context;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.handen.trends.PostActivity;
import com.handen.trends.R;
import com.handen.trends.adapters.TilesAdapter;
import com.handen.trends.data.Post;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TilesFragment.OnTileClickListener} interface
 * to handle interaction events.
 * Use the {@link TilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TilesFragment extends Fragment implements Parcelable {
    private static final String ARGS_POSTS = "posts";
    private ArrayList<Post> posts;
    private OnTileClickListener mListener;
    RecyclerView recyclerView;

    public static TilesFragment newInstance(ArrayList<Post> posts) {
        TilesFragment fragment = new TilesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_POSTS, (Serializable) posts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            posts = (ArrayList<Post>) getArguments().getSerializable(ARGS_POSTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiles, container, false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            TilesAdapter tilesAdapter = new TilesAdapter(posts, mListener, width);
            recyclerView.setAdapter(tilesAdapter);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTileClickListener) {
            mListener = (OnTileClickListener) context;
        }
    }

    @Override public void onDetach () {
            super.onDetach();
            mListener = null;
        }

        @Override public int describeContents () {
            return 0;
        }

        @Override public void writeToParcel (Parcel dest,int flags){

        }
        public interface OnTileClickListener {
            void startPostActivity(int clickPosition, ArrayList<Post> posts);
        }
    }

