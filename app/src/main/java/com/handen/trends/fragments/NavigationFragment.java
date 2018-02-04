package com.handen.trends.fragments;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.handen.trends.R;



/**
 * Created by Vanya on 26.11.2017.
 * <p></p>
 * Чтобы добавить новый фрагмент в NavigationDrawer, наследуем фрагмент от этого класса, копируем
 * разметку из уже существующего, оставляем AppbarLayout and Fab, переопределяем метод findViews,
 * находим toolbar, устанавливаем setTitle.
 * Возможно, для наших вью делаем android:layout_marginTop="?attr/actionBarSize", иначе контент будет
 * не вмещаться.
 */

public class NavigationFragment extends android.support.v4.app.Fragment {

    protected View view;
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    protected Toolbar toolbar;
    protected OnNavigationItemClick mNavigationClickListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationItemClick) {
            mNavigationClickListener = (OnNavigationItemClick) context;
        }
        else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mNavigationClickListener = null;
    }

    public void findViews(View view) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(mNavigationClickListener);
    }

    public interface OnNavigationItemClick extends NavigationView.OnNavigationItemSelectedListener {

    }
}
