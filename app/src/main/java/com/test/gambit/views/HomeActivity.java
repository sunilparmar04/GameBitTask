package com.test.gambit.views;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.test.gambit.R;
import com.test.gambit.adapters.ViewPagerAdapter;
import com.test.gambit.fragments.GamesFragment;
import com.test.gambit.fragments.PlayersFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilparmar on 4/27/2019.
 */

public class HomeActivity extends AppCompatActivity {

    public TabLayout mTabLayout;
    private ViewPager mViewpager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        setupTabLayout();
    }

    private void initViews() {
        mTabLayout = findViewById(R.id.tablayout);
        mViewpager = findViewById(R.id.viewpager);
    }

    private void setupTabLayout() {

        TabLayout.Tab tab1 = mTabLayout.newTab();
        TabLayout.Tab tab2 = mTabLayout.newTab();
        tab1.setCustomView(R.layout.badged_tab);
        tab2.setCustomView(R.layout.badged_tab);

        mTabLayout.addTab(tab1.setText(getString(R.string.tab_players)));
        mTabLayout.addTab(tab2.setText(getString(R.string.tab_games)));

        List<Fragment> fragments = new ArrayList<>();


        PlayersFragment playersFragment = new PlayersFragment();

        fragments.add(playersFragment);
        GamesFragment gameFragment = new GamesFragment();
        fragments.add(gameFragment);
        adapter = new ViewPagerAdapter
                (getSupportFragmentManager(), mTabLayout.getTabCount(), fragments);

        mViewpager.setAdapter(adapter);

        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewpager.setCurrentItem(tab.getPosition());


                changeTabTextStyle(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void changeTabTextStyle(int position) {
        TextView textView1 = mTabLayout.getTabAt(0).getCustomView().findViewById(android.R.id.text1);
        textView1.setTextAppearance(this, R.style.ArialMT);
        textView1.setTextColor(getResources().getColor(R.color.color_9B9B9B));

        textView1.setText(getString(R.string.tab_players));
        TextView textView2 = mTabLayout.getTabAt(1).getCustomView().findViewById(android.R.id.text1);
        textView2.setTextAppearance(this, R.style.ArialMT);
        textView2.setTextColor(getResources().getColor(R.color.color_9B9B9B));

        textView2.setText(getString(R.string.tab_games));

        if (position == 0) {
            textView1.setTextAppearance(this, R.style.ArialMT);
            textView1.setTextColor(getResources().getColor(R.color.color_4D55FF));
            textView1.setText(getString(R.string.tab_players));
        } else if (position == 1) {
            textView2.setTextAppearance(this, R.style.ArialMT);
            textView2.setTextColor(getResources().getColor(R.color.color_4D55FF));
            textView2.setText(getString(R.string.tab_games));
        }

    }

}