package com.example.lucas.fbsearch;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapterFav extends FragmentPagerAdapter {

    int tabCount;

    public TabPagerAdapterFav(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                UsersFragmentFav usersTab = new UsersFragmentFav();
                return usersTab;
            case 1:
                PagesFragmentFav pagesTab = new PagesFragmentFav();
                return pagesTab;
            case 2:
                EventsFragmentFav eventsTab = new EventsFragmentFav();
                return eventsTab;
            case 3:
                PlacesFragmentFav placesTab = new PlacesFragmentFav();
                return placesTab;
            case 4:
                GroupsFragmentFav groupsTab = new GroupsFragmentFav();
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