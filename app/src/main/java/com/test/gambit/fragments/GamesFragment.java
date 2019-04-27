package com.test.gambit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.gambit.R;

/**
 * Created by sunilparmar on 4/27/2019.
 */

public class GamesFragment extends Fragment {
    private View mView;
    private RecyclerView mGameRecycleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_games, container, false);
        initViews();
        return mView;
    }

    private void initViews() {
        mGameRecycleView = mView.findViewById(R.id.gameRecyclerView);
    }
}
