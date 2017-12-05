package com.handen.trends.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handen.trends.PostComparator;
import com.handen.trends.R;
import com.handen.trends.adapters.TilesAdapter;
import com.handen.trends.TilesLayoutManager;
import com.handen.trends.data.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TilesFragment.OnTileClickListener} interface
 * to handle interaction events.
 * Use the {@link TilesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TilesFragment extends Fragment implements Parcelable{

    private static final String ARGS_POSTS = "posts";

    private ArrayList<Post> posts;

    private OnTileClickListener mListener;
    RecyclerView recyclerView;


    public TilesFragment() {
        // Required empty public constructor
    }


    public static TilesFragment newInstance(ArrayList<Post> posts) {
        TilesFragment fragment = new TilesFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGS_POSTS, (Parcelable) posts);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            posts = (ArrayList<Post>) getArguments().getParcelable(ARGS_POSTS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tiles, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;

            TilesLayoutManager manager = new TilesLayoutManager();

            recyclerView.setLayoutManager(manager);

            TilesAdapter tilesAdapter = new TilesAdapter(posts, mListener);

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

/*        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

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
    public interface OnTileClickListener {

        void startPostActivity(int clickPosition, ArrayList<Post> posts);
    }
}
