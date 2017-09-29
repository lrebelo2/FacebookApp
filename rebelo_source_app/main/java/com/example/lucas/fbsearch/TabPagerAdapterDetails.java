package com.example.lucas.fbsearch;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapterDetails extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapterDetails(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AlbumsFragment albumsTab = new AlbumsFragment();
                return albumsTab;
            case 1:
                PostsFragment postsTab = new PostsFragment();
                return postsTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}