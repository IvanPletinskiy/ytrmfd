package com.handen.trends.patterns;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

/**
 * Created by Vanya on 05.12.2017.
 */

public abstract class Pattern extends RecyclerView.ViewHolder {

    public Pattern(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        super(itemView);
    }
}
