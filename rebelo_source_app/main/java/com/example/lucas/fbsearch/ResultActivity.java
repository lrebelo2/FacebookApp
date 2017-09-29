package com.example.lucas.fbsearch;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.example.lucas.fbsearch.R.id.container;

@SuppressWarnings("deprecation")
public class ResultActivity extends AppCompatActivity implements UsersFragment.OnFragmentInteractionListener, PagesFragment.OnFragmentInteractionListener, EventsFragment.OnFragmentInteractionListener, PlacesFragment.OnFragmentInteractionListener, GroupsFragment.OnFragmentInteractionListener {


    public static Response pagesdata;
    public static Response usersdata;
    public static Response eventsdata;
    public static Response placesdata;
    public static Response groupsdata;
    public String response;
    private ViewPager mViewPager;
    public String keyword;
    public PagerAdapter adapter;
    public TabLayout tabLayout;
    public static boolean done = false;
    public static double lat, lng;
    public GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        response = "";
        usersdata = null;
        pagesdata = null;
        groupsdata = null;
        placesdata = null;
        eventsdata = null;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intentParent = getIntent();
        keyword = intentParent.getStringExtra("keyword").trim().replace(" ", "").toLowerCase();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        googleApiClient = new GoogleApiClient.Builder(this, new GoogleApiClient.ConnectionCallbacks() {
            @Override
            public void onConnected(@Nullable Bundle bundle) {

                if (ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                    lat = lastLocation.getLatitude();
                    lng = lastLocation.getLongitude();
                }
            }

            @Override
            public void onConnectionSuspended(int i) {
                lat = 34.0216685;
                lng = -118.2826306;
            }
        }, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                lat = 34.0216685;
                lng = -118.2826306;
            }
        }).addApi(LocationServices.API).build();

        String url = "http://lowcost-env.wzpsud4a37.us-west-2.elasticbeanstalk.com/index.php?keyword=" + keyword + "&lat=" + lat + "&long=" + lng;
        HttpGetRequestR request = new HttpGetRequestR();
        request.execute(url);


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    public void onBackPressed() {
        done = false;
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_to_right, R.anim.slide_from_left);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Lat: " + lat + "\nLong: " + lng, Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    private class HttpGetRequestR extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

            tabLayout = (TabLayout) findViewById(R.id.tab_layout);
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
            adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(container);
            mViewPager.setAdapter(adapter);
            mViewPager.addOnPageChangeListener(new
                    TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.setOnTabSelectedListener(new
                                                       TabLayout.OnTabSelectedListener() {
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
            super.onPreExecute();
        }

        @Override
        public String doInBackground(String... params) {
            String stringUrl = params[0];
            try {
                URL url = new URL(stringUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = in.readLine()) != null) {

                    response.append(line);
                }

                in.close();
                return response.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            done = true;
            response = result;
            Gson gson = new GsonBuilder().create();
            Map<String, String> resMap = gson.fromJson(response, new TypeToken<HashMap<String, String>>() {
            }.getType());
            usersdata = gson.fromJson(resMap.get("users"), Response.class);
            pagesdata = gson.fromJson(resMap.get("pages"), Response.class);
            groupsdata = gson.fromJson(resMap.get("groups"), Response.class);
            placesdata = gson.fromJson(resMap.get("places"), Response.class);
            eventsdata = gson.fromJson(resMap.get("events"), Response.class);
            adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(container);
            mViewPager.setAdapter(adapter);
            mViewPager.addOnPageChangeListener(new
                    TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            super.onPostExecute(result);
        }

    }
}


