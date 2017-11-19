package com.handen.trends;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.handen.trends.data.Post;

import java.util.ArrayList;

/**
 * Created by Vanya on 15.10.2017.
 */

public class TilesAdapter extends RecyclerView.Adapter<TilesAdapter.ViewHolder> {

    private ArrayList<Post> posts;

    private TilesFragment.OnTileClickListener mListener;


    public TilesAdapter(ArrayList<Post> posts, TilesFragment.OnTileClickListener listener) {
        this.posts = posts;
        this.mListener = listener;
    }

    @Override
    public TilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TilesAdapter.ViewHolder holder, int position) {

        holder.post = posts.get(position);

        holder.button.setText(Integer.toString(holder.getAdapterPosition()) +" " + holder.post.getTitle());

        if( holder.getAdapterPosition() % 2 == 0)
            holder.button.setBackgroundColor(Color.RED);
        else
            holder.button.setBackgroundColor(Color.GREEN);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.startPostActivity(holder.getAdapterPosition(), posts);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private Post post;
        private Button button;
    //    public final TextView noteTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            button = (Button) view.findViewById(R.id.tile);

        //    noteTextView = (TextView) view.findViewById(R.id.note);
        }

    }

}
