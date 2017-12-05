package com.handen.trends.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.handen.trends.PostComparator;
import com.handen.trends.R;
import com.handen.trends.data.Post;
import com.handen.trends.fragments.TilesFragment;
import com.handen.trends.patterns.Pattern;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Vanya on 15.10.2017.
 */

public class TilesAdapter extends RecyclerView.Adapter<TilesAdapter.ViewHolder> {

    private ArrayList<Post> posts;
    private ArrayList<Pattern> patterns;

    private PostComparator postComparator;

    private TilesFragment.OnTileClickListener mListener;

    public TilesAdapter(ArrayList<Post> posts, TilesFragment.OnTileClickListener listener) {
        this.posts = posts;
        this.mListener = listener;
        generatePatterns();
    }

    @Override
    public TilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tile, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TilesAdapter.ViewHolder holder, int position) {

        holder.post = posts.get(position);

        holder.button.setText(Integer.toString(holder.getAdapterPosition()) + " " + holder.post.getTitle());

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

    @Override
    public int getItemViewType(int position) {

    }


    private void generatePatterns() {
        sortPosts();
        fillPatternsWithPosts();
        Collections.shuffle(patterns);
    }

    private void sortPosts() {
        postComparator = new PostComparator(getAveragePopularity(), getAveragePeriod());
        Collections.sort(posts, postComparator);

    }

    private void fillPatternsWithPosts() {
        ArrayList<Post> bufferList = new ArrayList<>();
        while (posts.size() != 0) {

            if(posts.size() == 1) {
                bufferList.add(posts.get(0));
                posts.remove(0);
                patterns.add(new Pattern())
                continue;
            }
            if(posts.size() == 2) {
                continue;
            }

        }
    }


    private float getAveragePopularity() {
        float totalPopularity = 0;
        for(Post post : posts) {
            totalPopularity += post.getPopularity();
        }
        return totalPopularity / posts.size();
    }


    private float getAveragePeriod() {
        long totalPeriod = 0;
        for (Post post: posts) {
            totalPeriod += post.getPeriod();
        }
        return totalPeriod / posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private Post post;
        private Button button;
    //    public final TextView noteTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
   //         button = (Button) view.findViewById(R.id.tile);

        //    noteTextView = (TextView) view.findViewById(R.id.note);
        }

    }

}
