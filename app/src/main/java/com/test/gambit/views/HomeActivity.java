package com.test.gambit.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.test.gambit.R;
import com.test.gambit.fragments.GamesFragment;
import com.test.gambit.fragments.PlayersFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilparmar on 4/27/2019.
 */

public class HomeActivity extends AppCompatActivity{

    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        setupTabLayout();
    }

    private void initViews(){
        mTabLayout=findViewById(R.id.tablayout);
        mViewpager=findViewById(R.id.viewpager);
    }

    private void setupTabLayout() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        PlayersFragment playersFragment=new PlayersFragment();
        adapter.addFragment(playersFragment, "Players");
        GamesFragment gameFragment=new GamesFragment();
        adapter.addFragment(gameFragment, "Game");
        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public List<Fragment> getmFragmentList() {
            return mFragmentList;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
