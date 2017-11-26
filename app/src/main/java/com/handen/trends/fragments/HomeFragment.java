package com.handen.trends.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handen.trends.R;
import com.handen.trends.adapters.TabAdapter;

import java.util.ArrayList;

public class HomeFragment extends NavigationFragment {
    private static final String ARGS_FRAGMENTS = "fragments";
    private static final String ARGS_TITLES = "titles";

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private PagerAdapter pagerAdapter;

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;

    public HomeFragment() {
    }

    @SuppressWarnings("unused")
    public static HomeFragment newInstance(ArrayList<Fragment> fragments, ArrayList<String> titles) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_FRAGMENTS, fragments);
        args.putStringArrayList(ARGS_TITLES, titles);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragments = (ArrayList<Fragment>) getArguments().getSerializable(ARGS_FRAGMENTS);
            titles = getArguments().getStringArrayList(ARGS_TITLES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findView(view);
        return view;

    }


    @Override
    public void findView(View view) {
        super.findView(view);

        viewPager= (ViewPager) view.findViewById(R.id.viewPager);
        pagerAdapter = new TabAdapter(fragments, titles,
                getActivity().getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tbl_pages);
        tabLayout.setupWithViewPager(viewPager);

        ((AppCompatActivity) getActivity())
                .getSupportActionBar()
                .setTitle(R.string.main);

    }
}
