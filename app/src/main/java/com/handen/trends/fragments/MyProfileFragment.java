package com.handen.trends.fragments;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handen.trends.ClientInterface;
import com.handen.trends.R;
import com.handen.trends.adapters.TabAdapter;
import com.handen.trends.data.Post;
import com.handen.trends.data.User;
import com.handen.trends.userActivity.UserAboutFragment;

import java.util.ArrayList;

import static com.handen.trends.ClientInterface.currentUserId;
import static com.handen.trends.ClientInterface.getUser;
import static com.handen.trends.ClientInterface.getUserPosts;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileFragment extends NavigationFragment {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private User user;
    private ArrayList<Post> userPosts;

    public MyProfileFragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    public static MyProfileFragment newInstance() {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        findView(view);


        ArrayList<Fragment> fragments = new ArrayList<>();
        user = getUser(0);
        userPosts = getUserPosts(user.getId());
        fragments.add(TilesFragment.newInstance(userPosts));

        fragments.add(UserAboutFragment.newInstance(user.getDescription(), user.getRegistrationDate(),
                0, userPosts.size(), getTotalLikes()));
        ArrayList<String> titles = new ArrayList<>();
        titles.add(getString(R.string.trends));
        titles.add(getString(R.string.aboutUser));

        viewPager.setAdapter(new TabAdapter(fragments, titles, getActivity().getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private long getTotalLikes() {
        long totalLikes = 0;

        for(Post post : userPosts) {
            totalLikes += post.getLikes();
        }

        return totalLikes;
    }



    @Override
    public void findView(View view) {
        super.findView(view);
        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar_fragment_my_profile);
        collapsingToolbarLayout.setTitle(ClientInterface.getUser(currentUserId).getNickName());

        viewPager = (ViewPager) view.findViewById(R.id.view_pager_fragment_my_profile);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout_fragment_my_profile);

        System.err.println("FindView");

    }
}
