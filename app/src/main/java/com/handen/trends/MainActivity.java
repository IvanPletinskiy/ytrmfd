package com.handen.trends;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.handen.trends.data.Post;
import com.handen.trends.fragments.HomeFragment;
import com.handen.trends.fragments.LikedFragment;
import com.handen.trends.fragments.MyProfileFragment;
import com.handen.trends.fragments.NavigationFragment;
import com.handen.trends.fragments.TilesFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationFragment.OnNavigationItemClick, TilesFragment.OnTileClickListener {

    private HomeFragment homeFragment;
    private LikedFragment likedFragment;
    private MyProfileFragment myProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        homeFragment = HomeFragment.newInstance();
        myProfileFragment = MyProfileFragment.newInstance();

        displayFragment(homeFragment, HomeFragment.TAG_HOME);
    }

    @SuppressWarnings ("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_nav:
                homeFragment = HomeFragment.newInstance();
                displayFragment(homeFragment, HomeFragment.TAG_HOME);
                break;
            case R.id.liked_nav:
                likedFragment = LikedFragment.newInstance();
                displayFragment(likedFragment, LikedFragment.TAG_LIKED);
                break;
            case R.id.myProfile_nav:
                myProfileFragment = MyProfileFragment.newInstance();
                displayFragment(myProfileFragment, MyProfileFragment.TAG_MY_PROFILE);
                break;
            case R.id.shop_nav:
                break;
            case R.id.bugReport_nav:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayFragment(Fragment fragment, String TAG) {
        Fragment currentFragment = MainActivity.this.getSupportFragmentManager().findFragmentById(R.id.fragment_host_coordinator_layout);

        if (currentFragment == null || TAG.equals(HomeFragment.TAG_HOME)) {
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
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                getSupportFragmentManager().getBackStackEntryCount();
                finish();
            }
            else {
                super.onBackPressed();
            }
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
