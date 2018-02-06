package com.handen.trends.adapters;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.handen.trends.R;
import com.handen.trends.data.Category;

import java.util.ArrayList;

/**
 * Created by Vanya on 04.02.2018.
 * <p></p>
 * Используется в spinner`е с категориями
 */

public class CategoriesListAdapter extends BaseAdapter implements ListAdapter, Filterable {
    ArrayList<Category> categories;

    public CategoriesListAdapter(ArrayList<Category> list) {
        categories = list;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories_spinner, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(categories.get(position).getName());
        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return categories.isEmpty();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                // Create a FilterResults object
                FilterResults results = new FilterResults();

                // If the constraint (search string/pattern) is null
                // or its length is 0, i.e., its empty then
                // we just set the `values` property to the
                // original contacts list which contains all of them
                if (constraint == null || constraint.length() == 0) {
                    results.values = categories;
                    results.count = categories.size();
                }
                else {
                    // Some search copnstraint has been passed
                    // so let's filter accordingly
                    ArrayList<Category> filteredCategories = new ArrayList<Category>();

                    // We'll go through all the contacts and see
                    // if they contain the supplied string
                    for (Category c : categories) {
                        if (c.getName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                            // if `contains` == true then add it
                            // to our filtered list
                            filteredCategories.add(c);
                        }
                    }

                    // Finally set the filtered values and size/count
                    results.values = filteredCategories;
                    results.count = filteredCategories.size();
                }
                // Return our FilterResults object
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                categories = (ArrayList<Category>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
