package com.github.fallblank.ganklast.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.github.fallblank.ganklast.R;
import com.github.fallblank.ganklast.presenter.LoadHistoryPresenter;
import com.github.fallblank.ganklast.ui.fragment.GirlFragment;
import com.github.fallblank.ganklast.ui.fragment.TypeFragment;
import com.github.fallblank.ganklast.ui.fragment.DailyPagerFragment;
import com.github.fallblank.ganklast.ui.fragment.TypePagerFragment;
import com.github.fallblank.ganklast.ui.view.FB_LoadView;
import com.github.fallblank.ganklast.util.Type;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, FB_LoadView {
    private static final String TAG = "MainActivity";
    /**
     * the published date list
     */
    private ArrayList<String> mHistoryList = new ArrayList<>();

    private LoadHistoryPresenter mLoadPresenter = new LoadHistoryPresenter(this, this);

    private MenuItem mProgressIndicator;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mLoadPresenter.getHistory();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mProgressIndicator = menu.findItem(R.id.action_progress);
        mProgressIndicator.setActionView(R.layout.action_view_progress);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        startFrament(id);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private static final String TAG_RECOMMEND = "recommend";
    private static final String TAG_TYPE = "TYPE";
    private static final String TAG_GIRL = "girl";


    private Fragment mCurrentFrament = null;

    private void startFrament(int id) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container);
        switch (id) {
            case R.id.nav_recommend:
                if (fragment == null || !(fragment instanceof DailyPagerFragment)) {
                    fragment = DailyPagerFragment.newInstance(mHistoryList);
                    fm.beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                }
                mToolbar.setTitle(R.string.recommend);
                break;
            case R.id.nav_type:
                if (fragment == null || !(fragment instanceof TypeFragment)) {
                    fragment = new TypePagerFragment();
                    fm.beginTransaction()
                            .replace(R.id.container, fragment, TAG_TYPE)
                            .commit();
                }
                mToolbar.setTitle(R.string.type);
                break;
            case R.id.nav_girl:
                if (fragment == null || !(fragment instanceof GirlFragment)) {
                    fragment = new GirlFragment();
                    fm.beginTransaction()
                            .replace(R.id.container, fragment, TAG_GIRL)
                            .commit();
                }
                mToolbar.setTitle(R.string.girl);
                break;
            default:
                break;
        }
    }

    @Override
    public void show() {
        Log.i(TAG, "loading");
        if (mProgressIndicator != null) {
            mProgressIndicator.setVisible(true);
        }
    }

    @Override
    public void success() {
        Log.i(TAG, "success");
        mProgressIndicator.setVisible(false);
        startFrament(R.id.nav_recommend);
    }

    @Override
    public void error(Throwable e) {
        Log.e(TAG, "error", e);
        mProgressIndicator.setVisible(false);
    }

    @Override
    public void loadFinish(List<String> list) {
        mHistoryList.clear();
        mHistoryList.addAll(0, list);
    }
}
