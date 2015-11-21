package com.mick88.convoytrucking;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mick88.convoytrucking.base.BaseActivity;
import com.mick88.convoytrucking.base.BaseFragment;
import com.mick88.convoytrucking.chat.ChatFragment;
import com.mick88.convoytrucking.server_info.ServerInfoFragment;

/**
 * Created by Michal on 03/11/2015.
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavDrawer();

        if (savedInstanceState == null) {
            BaseFragment fragment = new ServerInfoFragment();
            showFragment(fragment);
        }
    }

    protected <T extends BaseFragment> void showFragment(T fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
    }

    void setupNavDrawer() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.navDrawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_closed);
        drawer.setDrawerListener(drawerToggle);

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        BaseFragment fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_chat:
                fragment = new ChatFragment();
                break;

            case R.id.menu_server_info:
                fragment = new ServerInfoFragment();
                break;
        }
        if (fragment != null) {
            showFragment(fragment);
            drawer.closeDrawers();
            item.setChecked(true);
            return true;
        }
        return false;
    }
}
