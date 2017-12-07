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
import com.handen.trends.patterns.Row;
import com.handen.trends.patterns.Row1;
import com.handen.trends.patterns.Row2;
import com.handen.trends.patterns.Row3;
import com.handen.trends.patterns.Row4;
import com.handen.trends.patterns.Row5;

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
            //xmlResource = pattern_2

        }

   //     myGraphView.setLayoutParams(new LayoutParams(width, height));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TilesAdapter.ViewHolder holder, int position) {

        int rowType = rows.get(position).getId();

 //       for (Post p: posts) {
 //           System.err.println(p.getTitle());
    //    }

        switch (rowType) {
            case 1:
    //            holder.mView.findViewById(R.id.tile1);
                break;
            case 2:
     //           holder.mView.findViewById(R.id.tile1);
     //           holder.mView.findViewById(R.id.tile2);
                break;

        }

/*        holder.post = posts.get(position);

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
*/

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
       // Collections.shuffle(rows);
    }

    private void sortPosts() {
        postComparator = new PostComparator(getAveragePopularity(), getAverageHours());
        Collections.sort(posts, postComparator);

        System.err.println(posts);

    }

    private void fillPatternsWithPosts() {
        int i = 0;
        for(; i < posts.size() - 3; i += 3) {
            Post p1 = posts.get(i);
            Post p2 = posts.get(i + 1);
            Post p3 = posts.get(i + 2);

            int positiveCount = getPositiveCount(p1, p2, p3);
            switch (positiveCount) {
                case 3 :
                    rows.add(new Row1());
                    rows.add(new Row2());
                    break;
                case 2 :
                    rows.add(new Row3());
                    break;
                case 1 :
                    rows.add(new Row4());
                    break;
                case 0 :
                    rows.add(new Row5());
                    break;
            }
        }
        if(i == posts.size() - 2) {
            //Один элемент
        }
        if(i == posts.size() - 3) {
            //Два элемента
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
