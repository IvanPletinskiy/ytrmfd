package com.handen.trends;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.handen.trends.data.Post;
import com.handen.trends.fragments.HomeFragment;
import com.handen.trends.fragments.MyProfileFragment;
import com.handen.trends.fragments.NavigationFragment;
import com.handen.trends.fragments.TilesFragment;

import java.util.ArrayList;

import static com.handen.trends.ClientInterface.currentUserId;
import static com.handen.trends.ClientInterface.getPosts;
import static com.handen.trends.ClientInterface.getSubscribedPosts;
import static com.handen.trends.ClientInterface.getUser;

public class MainActivity extends AppCompatActivity implements NavigationFragment.OnNavigationItemClick, TilesFragment.OnTileClickListener {

    private CoordinatorLayout fragmentHostCoordinatorLayout;

    private HomeFragment homeFragment;
    private MyProfileFragment myProfileFragment;

    private static final String TAG_HOME = "home";
    private static final String TAG_MY_PROFILE = "myProfile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentHostCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.fragment_host_coordinator_layout);
        //TODO 01.02.18 убрать fragments, titles из конструктора HomeFragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(TilesFragment.newInstance(getPosts(0)));
        fragments.add(TilesFragment.newInstance(getSubscribedPosts()));
        fragments.add(TilesFragment.newInstance(getPosts(2)));

        ArrayList<String> titles = new ArrayList<>();
        titles.add(getString(R.string.world));
        titles.add(getString(R.string.subscribtions));
        titles.add(getUser(currentUserId).getRegionTitle());

        homeFragment = HomeFragment.newInstance(fragments, titles);
        myProfileFragment = MyProfileFragment.newInstance();

        displayFragment(homeFragment, TAG_HOME);
    }

    @SuppressWarnings ("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main_nav) {
            displayFragment(homeFragment, TAG_HOME);
        }
        else {
            if (id == R.id.liked_nav) {

            }
            else {
                if (id == R.id.myProfile_nav) {
                    displayFragment(myProfileFragment, TAG_MY_PROFILE);
                }
                else {
                    if (id == R.id.shop_nav) {

                    }
                    else {
                        if (id == R.id.bugReport_nav) {

                        }
                        else {
                            if (id == R.id.logout_nav) {

                            }
                            else {
                                if (id == R.id.nav_share) {

                                }
                                else {
                                    if (id == R.id.nav_send) {

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    public void displayFragment(Fragment fragment, String TAG) {
        Fragment currentFragment = MainActivity.this.getSupportFragmentManager().findFragmentById(R.id.fragment_host_coordinator_layout);

        if (currentFragment == null || TAG.equals(TAG_HOME)) {
            getSupportFragmentManager().beginTransaction().addToBackStack(TAG).replace(R.id.fragment_host_coordinator_layout, fragment, TAG).commit();
            return;

        }

        if (!TAG.equals(currentFragment.getTag())) {
            getSupportFragmentManager().beginTransaction().addToBackStack(TAG).replace(R.id.fragment_host_coordinator_layout, fragment, TAG).commit();
        }

    }

    @Override
    public void startPostActivity(int clickPosition, ArrayList<Post> posts) {
        Intent intent = new Intent(MainActivity.this, PostActivity.class);
        intent.putExtra(PostActivity.ARGS_POST_POSITION, clickPosition);
        intent.putExtra(PostActivity.ARGS_POSTS, posts);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
