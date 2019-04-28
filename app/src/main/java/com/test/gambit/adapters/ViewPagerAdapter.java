package com.test.gambit.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by sunilparmar on 4/28/2019.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs, List<Fragment> fragments) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}


