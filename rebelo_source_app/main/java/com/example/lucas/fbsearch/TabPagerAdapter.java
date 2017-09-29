package com.example.lucas.fbsearch;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                UsersFragment usersTab = new UsersFragment();
                return usersTab;
            case 1:
                PagesFragment pagesTab = new PagesFragment();
                return pagesTab;
            case 2:
                EventsFragment eventsTab = new EventsFragment();
                return eventsTab;
            case 3:
                PlacesFragment placesTab = new PlacesFragment();
                return placesTab;
            case 4:
                GroupsFragment groupsTab = new GroupsFragment();
                return groupsTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}