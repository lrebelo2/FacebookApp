package com.example.lucas.fbsearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import static com.example.lucas.fbsearch.R.id.container;
import static com.example.lucas.fbsearch.R.id.containerfav;
@SuppressWarnings("deprecation")
public class FavoriteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,UsersFragmentFav.OnFragmentInteractionListener, PagesFragmentFav.OnFragmentInteractionListener, EventsFragmentFav.OnFragmentInteractionListener, PlacesFragmentFav.OnFragmentInteractionListener, GroupsFragmentFav.OnFragmentInteractionListener {

    private ViewPager mViewPager;
    public PagerAdapter adapter;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = getLayoutInflater().inflate(R.layout.content_favorite,null);
        getLayoutInflater().inflate(R.layout.app_bar_favorite,null);
        setContentView(R.layout.activity_favorite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_fav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_fav);
        //UsersTab
        TabLayout.Tab userstab = tabLayout.newTab();
        userstab.setText("Users");
        userstab.setIcon(R.drawable.users);
        tabLayout.addTab(userstab);
        //PagesTab
        TabLayout.Tab pagestab = tabLayout.newTab();
        pagestab.setText("Pages");
        pagestab.setIcon(R.drawable.pages);
        tabLayout.addTab(pagestab);
        //EventsTab
        TabLayout.Tab eventstab = tabLayout.newTab();
        eventstab.setText("Events");
        eventstab.setIcon(R.drawable.events);
        tabLayout.addTab(eventstab);
        //PlacesTab
        TabLayout.Tab placestab = tabLayout.newTab();
        placestab.setText("Places");
        placestab.setIcon(R.drawable.places);
        tabLayout.addTab(placestab);
        //GroupsTab
        TabLayout.Tab groupstab = tabLayout.newTab();
        groupstab.setText("Groups");
        groupstab.setIcon(R.drawable.groups);
        tabLayout.addTab(groupstab);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adapter = new TabPagerAdapterFav(getSupportFragmentManager(), tabLayout.getTabCount());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.containerfav);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                                       @Override
                                                       public void onTabSelected(TabLayout.Tab tab) {
                                                           mViewPager.setCurrentItem(tab.getPosition());
                                                       }

                                                       @Override
                                                       public void onTabUnselected(TabLayout.Tab tab) {

                                                       }

                                                       @Override
                                                       public void onTabReselected(TabLayout.Tab tab) {

                                                       }

                                                   });
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
        getMenuInflater().inflate(R.menu.favorite, menu);
        return true;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settingsfav) {
            return true;
        }
        if (id == R.id.clearfav){
            Main2Activity.preferences.edit().clear().apply();
            Toast.makeText(this,"Favorites Cleared",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_to_up, R.anim.slide_from_down);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_favorites) {
            onBackPressed();
        } else if (id == R.id.nav_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_to_up, R.anim.slide_from_down);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
