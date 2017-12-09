package com.handen.trends.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.handen.trends.PostComparator;
import com.handen.trends.R;
import com.handen.trends.data.Post;
import com.handen.trends.fragments.TilesFragment;
import com.handen.trends.rows.Row;
import com.handen.trends.rows.Row1;
import com.handen.trends.rows.Row2;
import com.handen.trends.rows.Row3;
import com.handen.trends.rows.Row4;
import com.handen.trends.rows.Row5;
import com.handen.trends.rows.Row6;
import com.handen.trends.rows.Row7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by Vanya on 15.10.2017.
 */

public class TilesAdapter extends RecyclerView.Adapter<TilesAdapter.ViewHolder> {

    /*
    Нужно создавать Patterns
     */

    private ArrayList<Post> posts;
    private ArrayList<Row> rows = new ArrayList<>();

    private PostComparator postComparator;

    private float cellHeight;

    private TilesFragment.OnTileClickListener mListener;

    public TilesAdapter(ArrayList<Post> posts, TilesFragment.OnTileClickListener listener, int recyclerWidth) {
        this.posts = posts;
        this.mListener = listener;
        cellHeight = recyclerWidth / 6;

        generateRows();
    }

    @Override
    public TilesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //switch по viewType, устанавливается высота ряда
        int layoutFileResourceId = 0;
        switch (viewType) {
            case 1:
                //xmlResource = pattern_1
                layoutFileResourceId = R.layout.pattern_1;
                break;
            case 2:
                layoutFileResourceId = R.layout.pattern_2;
                break;
            case 3:
                layoutFileResourceId = R.layout.pattern_3;
                break;
            case 4:
                layoutFileResourceId = R.layout.pattern_4;
                break;
            case 5:
                layoutFileResourceId = R.layout.pattern_5;
                break;
            case 6:
                layoutFileResourceId = R.layout.row_6;
                break;
            case 7:
                layoutFileResourceId = R.layout.row_7;
                break;
                //xmlResource = pattern_2

        }

        View view = LayoutInflater.from(parent.getContext()).inflate(layoutFileResourceId, parent, false);

        switch (viewType) {
            case 1:
                //xmlResource = pattern_1
                view.setLayoutParams(new LinearLayout.LayoutParams(Math.round(cellHeight * 6),
                        Math.round(cellHeight * 6)));
                break;
            case 2:
                view.setLayoutParams(new LinearLayout.LayoutParams(Math.round(cellHeight * 6),
                        Math.round(cellHeight * 3)));
                break;
            case 3:
                view.setLayoutParams(new LinearLayout.LayoutParams(Math.round(cellHeight * 6),
                        Math.round(cellHeight * 4)));
                break;
            case 4:
                view.setLayoutParams(new LinearLayout.LayoutParams(Math.round(cellHeight * 6),
                        Math.round(cellHeight * 4)));
                break;
            case 5:
                view.setLayoutParams(new LinearLayout.LayoutParams(Math.round(cellHeight * 6),
                        Math.round(cellHeight * 2)));
                break;
            case 6:
                view.setLayoutParams(new LinearLayout.LayoutParams(Math.round(cellHeight * 6),
                        Math.round(cellHeight * 2)));
                break;
            case 7:
                view.setLayoutParams(new LinearLayout.LayoutParams(Math.round(cellHeight * 6),
                        Math.round(cellHeight * 3)));
                break;
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TilesAdapter.ViewHolder holder, int position) {

        int rowType = rows.get(position).getId();

        ArrayList<Integer> postIndexes = rows.get(position).getIndexes();

        switch (rowType) {
            case 1:
                holder.tile1 = (Button) holder.mView.findViewById(R.id.tile_1);
                holder.id1 = postIndexes.get(0);
                break;
            case 2:
                holder.tile1 = (Button) holder.mView.findViewById(R.id.tile_1);
                holder.id1 = postIndexes.get(0);
                holder.tile2 = (Button) holder.mView.findViewById(R.id.tile_2);
                holder.id2 = postIndexes.get(1);
                break;
            case 3:
                holder.tile1 = (Button) holder.mView.findViewById(R.id.tile_1);
                holder.id1 = postIndexes.get(0);
                holder.tile2 = (Button) holder.mView.findViewById(R.id.tile_2);
                holder.id2 = postIndexes.get(1);
                holder.tile3 = (Button) holder.mView.findViewById(R.id.tile_3);
                holder.id3 = postIndexes.get(2);
                break;
            case 4:
                holder.tile1 = (Button) holder.mView.findViewById(R.id.tile_1);
                holder.id1 = postIndexes.get(0);
                holder.tile2 = (Button) holder.mView.findViewById(R.id.tile_2);
                holder.id2 = postIndexes.get(1);
                holder.tile3 = (Button) holder.mView.findViewById(R.id.tile_3);
                holder.id3 = postIndexes.get(2);
                break;
            case 5:
                holder.tile1 = (Button) holder.mView.findViewById(R.id.tile_1);
                holder.id1 = postIndexes.get(0);
                holder.tile2 = (Button) holder.mView.findViewById(R.id.tile_2);
                holder.id2 = postIndexes.get(1);
                holder.tile3 = (Button) holder.mView.findViewById(R.id.tile_3);
                holder.id3 = postIndexes.get(2);
                break;
            case 6:
                holder.tile1 = (Button) holder.mView.findViewById(R.id.tile_1);
                holder.id1 = postIndexes.get(0);
                break;
            case 7:
                holder.tile1 = (Button) holder.mView.findViewById(R.id.tile_1);
                holder.id1 = postIndexes.get(0);
                break;
        }

        holder.bindTiles();

    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    @Override
    public int getItemViewType(int position) {
        return rows.get(position).getId();
    }

    private void generateRows() {
        sortPosts();
        fillPatternsWithPosts();

    }

    private void sortPosts() {
        calculateAndSetPostWeight();
    //    postComparator = new PostComparator(getAveragePopularity(), getAverageHours());
        postComparator = new PostComparator();
        Collections.sort(posts, postComparator);
        System.err.println(posts);
    }

    private void fillPatternsWithPosts() {
        int i = 0;
        for(; i <= posts.size() - 3; i += 3) {
            Post p1 = posts.get(i);
            Post p2 = posts.get(i + 1);
            Post p3 = posts.get(i + 2);

            int positiveCount = getPositiveCount(p1, p2, p3);
            switch (positiveCount) {
                case 3 :
                    rows.add(new Row1(i));
                    rows.add(new Row2(i + 1, i + 2));
                    break;
                case 2 :
                    rows.add(new Row3(i, i + 1, i + 2));
                    break;
                case 1 :
                    rows.add(new Row4(i, i + 1, i + 2));
                    break;
                case 0 :
                    rows.add(new Row5(i, i + 1, i + 2));
                    break;
            }
        }
        if(i + 1 == posts.size()) {
            //Один элемент
            rows.add(new Row6(i));
        }
        if(i + 2 == posts.size()) {
            //Два элемента
            rows.add(new Row7(i));
        }
    }

    private void calculateAndSetPostWeight() {
        float averageHours = getAverageHours();
        float averagePopularity = getAveragePopularity();
        long currentMillis = new Date().getTime();
        float hourMillis = 3600000;
        float postPopularity;
        float postHours;
        float postMillis;
        for (Post p: posts) {
            postMillis = p.getPostDate().getTime();

            postPopularity = p.getPopularity();
            postHours = (currentMillis - postMillis) / hourMillis;
            if(postPopularity / averagePopularity > averageHours / postHours) {
                p.setPositive(true);
                p.setWeight(postPopularity, averagePopularity);
            }
            else {
                p.setWeight(averageHours, postHours);
            }
        }
    }

    private int getPositiveCount(Post p1, Post p2, Post p3) {
        int count = 0;
        if(p1.isPositive())
            count++;
        if(p2.isPositive())
            count++;
        if(p3.isPositive())
            count++;
        return count;
    }


    private float getAveragePopularity() {
        float totalPopularity = 0;
        for(Post post : posts) {
            totalPopularity += post.getPopularity();
        }
        return totalPopularity / posts.size();
    }

    private float getAverageHours() {
        float totalHours = 0;
        long currentMillis = new Date().getTime();
        for (Post post: posts) {
            totalHours += ((float) (currentMillis - post.getPeriod()) / 3600000);
        }
        return totalHours / posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private Button tile1;
        private Button tile2;
        private Button tile3;
        private int id1 = -1;
        private int id2 = -1;
        private int id3 = -1;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }

        private void bindTiles() {
            String postTitle = "emptyPostTitle";
            if(id1 != -1) {
                postTitle = posts.get(id1).getTitle();
                tile1.setText(postTitle);
            }
            if(id2 != -1) {
                postTitle = posts.get(id2).getTitle();
                tile2.setText(postTitle);
            }
            if(id3 != -1) {
                postTitle = posts.get(id3).getTitle();
                tile3.setText(postTitle);
            }
        }

    }

}
