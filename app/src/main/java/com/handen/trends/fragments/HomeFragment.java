package com.handen.trends.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handen.trends.R;
import com.handen.trends.WritePostActivity;
import com.handen.trends.adapters.TabAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import static com.handen.trends.ClientInterface.currentUserId;
import static com.handen.trends.ClientInterface.getPosts;
import static com.handen.trends.ClientInterface.getSubscribedPosts;
import static com.handen.trends.ClientInterface.getUser;

public class HomeFragment extends NavigationFragment {
    private static final String ARGS_FRAGMENTS = "fragments";
    private static final String ARGS_TITLES = "titles";

    public static final int REQUEST_WRITE_POST = 10;
    public static final int RESULT_CODE_WRITTEN = 11;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter pagerAdapter;

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private FloatingActionButton fab;

    public HomeFragment() {

    }

    @SuppressWarnings("unused")
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();

   //     args.putSerializable(ARGS_FRAGMENTS, fragments);
  //      args.putStringArrayList(ARGS_TITLES, titles);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      if (getArguments() != null) {
  //          fragments = (ArrayList<Fragment>) getArguments().getSerializable(ARGS_FRAGMENTS);
  //          titles = getArguments().getStringArrayList(ARGS_TITLES);
  //      }

        fragments = new ArrayList<>(Arrays.asList(
                TilesFragment.newInstance(getPosts(0)),
                TilesFragment.newInstance(getSubscribedPosts()),
                TilesFragment.newInstance(getPosts(2))
        ));

        titles = new ArrayList<>(Arrays.asList(
                getString(R.string.world),
                getString(R.string.subscribtions),
                getUser(currentUserId).getRegionTitle()
        ));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_WRITE_POST) {
            if (resultCode == RESULT_CODE_WRITTEN) {
                //TODO сейчас обновляются все вкладки, возможно стоит обновлять только одну
                pagerAdapter = new TabAdapter(fragments, titles, getActivity().getSupportFragmentManager());
                viewPager.setAdapter(pagerAdapter);
            }
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
        pagerAdapter.getItem(0);
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tbl_pages);
        tabLayout.setupWithViewPager(viewPager);

        ((AppCompatActivity) getActivity())
                .getSupportActionBar()
                .setTitle(R.string.main);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_goto_write_post_activity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WritePostActivity.class);
                startActivityForResult(intent, REQUEST_WRITE_POST);
            }
        });
    }
}
