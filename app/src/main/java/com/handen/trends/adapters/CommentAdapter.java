package com.handen.trends.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.handen.trends.R;
import com.handen.trends.data.Comment;
import com.handen.trends.fragments.PostFragment;

import java.util.ArrayList;

/**
 * Created by Vanya on 09.02.2018.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private ArrayList<Comment> comments;
    private PostFragment.PostFragmentListener mListener;

    public CommentAdapter(ArrayList<Comment> comments, PostFragment.PostFragmentListener listener) {
        this.comments = comments;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Comment comment = comments.get(position);
        holder.authorNickname.setText(comment.getUserNickname());
        holder.authorNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.startUserProfileActivity(comment.getUserId());
            }
        });
        holder.date.setText("Создай чёртов DateFormat!");
        holder.likes.setText(Integer.toString(comment.getLikes()));
        holder.likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comments.get(position).setLikes(1 + comment.getLikes());
                holder.likes.setText(Integer.toString(comments.get(position).getLikes()));

            }
        });
        holder.text.setText(comment.getText());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView authorNickname, date, likes, text;
        ImageView likeImageView;

        public ViewHolder(View view) {
            super(view);
            authorNickname = (TextView) view.findViewById(R.id.text_view_nickname);
            likes = (TextView) view.findViewById(R.id.likes_text_view);
            date = (TextView) view.findViewById(R.id.date_text_view);
            text = (TextView) view.findViewById(R.id.commentText);
            likeImageView = (ImageView) view.findViewById(R.id.likes_image_view);
        }
    }
}