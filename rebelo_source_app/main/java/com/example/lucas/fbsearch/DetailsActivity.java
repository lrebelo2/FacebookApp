package com.example.lucas.fbsearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutionException;

@SuppressWarnings("deprecation")
public class DetailsActivity extends AppCompatActivity implements AlbumsFragment.OnFragmentInteractionListener, PostsFragment.OnFragmentInteractionListener {


    public static String id;
    public String type;
    public Menu menu;
    public String response;
    public ViewPager mViewPager;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    public static ResponseDetails detailsdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intentParent = getIntent();
        id = intentParent.getStringExtra("id");
        type = intentParent.getStringExtra("type");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        //AlbumsTab
        TabLayout.Tab albumstab = tabLayout.newTab();
        albumstab.setText("Albums");
        albumstab.setIcon(R.drawable.albums);
        tabLayout.addTab(albumstab);
        //PostsTab
        TabLayout.Tab poststab = tabLayout.newTab();
        poststab.setText("Posts");
        poststab.setIcon(R.drawable.posts);
        tabLayout.addTab(poststab);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        PagerAdapter adapter = new TabPagerAdapterDetails(getSupportFragmentManager(), tabLayout.getTabCount());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
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
        response = requestDetails(id);
        Gson gson = new GsonBuilder().create();
        detailsdata = gson.fromJson(response, ResponseDetails.class);
    }

    public void onResume() {
        super.onResume();


    }


    public String requestDetails(String id) {
        String url;
        if (type.equalsIgnoreCase("event")) {
            url = "https://graph.facebook.com/v2.8/" + id + "?fields=name,picture.width(60).height(60),posts.limit(5)&access_token=EAAQ7likbczkBANXPrjETeKH7uFbkzpGUXuyLl8M3sndbCQsDTqi7wu7gol0DiKtmIuhFRs4Fe4y7HQZAAl9Dfi16zwLADn2kLhh6o33slstxPgP72X4bT5xAYNAZCfSWHcxQ4FG2zukAFr8NkgA6QLv0xzONQZD";
        } else {
            url = "https://graph.facebook.com/v2.8/" + id + "?fields=name,picture.width(60).height(60),albums.limit(5){name,photos.limit(2){name,picture}},posts.limit(5)&access_token=EAAQ7likbczkBANXPrjETeKH7uFbkzpGUXuyLl8M3sndbCQsDTqi7wu7gol0DiKtmIuhFRs4Fe4y7HQZAAl9Dfi16zwLADn2kLhh6o33slstxPgP72X4bT5xAYNAZCfSWHcxQ4FG2zukAFr8NkgA6QLv0xzONQZD";
        }
        HttpGetRequest request = new HttpGetRequest();
        request.execute(url);
        try {
            response = request.get();
            Log.d("response", response);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return response;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return response;
        }
        return response;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //you can leave it empty
    }


    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_to_down, R.anim.slide_from_up);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addFav) {


            boolean contains = Main2Activity.preferences.contains(detailsdata.getId());
            String alert;
            if (!contains) {
                ListModel lm = new ListModel();
                lm.setImage(detailsdata.getPicture().getData().getUrl());
                lm.setName(detailsdata.getName());
                lm.setId(detailsdata.getId());
                lm.setType(type);
                Gson gson = new GsonBuilder().create();
                String store = gson.toJson(lm);
                boolean add = Main2Activity.preferences.edit().putString(detailsdata.getId(), store).commit();
                if (add) {
                    alert = "Added to Favorites!";
                    menu.getItem(0).setTitle(R.string.remFav);
                } else {
                    alert = "Already in Favorites!";//most likely will never reach this
                }
            } else {

                boolean remove = Main2Activity.preferences.edit().remove(detailsdata.getId()).commit();
                if (remove) {
                    alert = "Removed from Favorites";
                    menu.getItem(0).setTitle(R.string.addFav);
                } else {
                    alert = "Not in Favorites!";//most likely will never reach this
                }
            }
            Toast toast = Toast.makeText(this, alert, Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        if (id == R.id.share) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse("http://facebook.com/" + detailsdata.getId()))
                    .setShareHashtag(new ShareHashtag.Builder()
                            .setHashtag("#ConnectTheWorld")
                            .build()).build();


            callbackManager = CallbackManager.Factory.create();
            shareDialog = new ShareDialog(this);
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                shareDialog.show(linkContent);
            }
            return true;
        }
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(getApplicationContext(), "You shared this post.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "You did not share this post. Canceled", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                exception.printStackTrace();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;

        getMenuInflater().inflate(R.menu.menu_details, menu);
        boolean contains = Main2Activity.preferences.contains(detailsdata.getId());
        String alert;
        if (contains) {
            menu.getItem(0).setTitle(R.string.remFav);
        } else {
            menu.getItem(0).setTitle(R.string.addFav);
        }
        return true;
    }


}
