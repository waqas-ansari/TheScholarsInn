package com.arktech.waqasansari.thescholarsinn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Linta Ansari on 7/8/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs = 2;
    Bundle bundle;

    public PagerAdapter(FragmentManager fm, Bundle bundle) {
        super(fm);
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentTestMarks();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new FragmentAttendance();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position){
            case 0:
                title = "Test Marks";
                break;
            case 1:
                title = "Attendance";
                break;
        }
        return title;
    }
}
