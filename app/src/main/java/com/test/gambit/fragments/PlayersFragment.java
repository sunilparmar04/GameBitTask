package com.test.gambit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.test.gambit.R;
import com.test.gambit.model.PlayersResponse;
import com.test.gambit.utils.AppConstants;
import com.test.gambit.utils.AppLogger;
import com.test.gambit.utils.CommonUtils;
import com.test.gambit.utils.NetworkUtils;

import java.util.HashMap;
import java.util.Map;

import in.myvolley.NetworkCall;
import in.myvolley.interfaces.NetworkCallback;

/**
 * Created by sunilparmar on 4/27/2019.
 */

public class PlayersFragment extends Fragment {

    private View mView;

    private RecyclerView mPlayerRecycleView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_players, container, false);
        initViews();
        getPlayers();
        return mView;
    }

    private void initViews() {
        mPlayerRecycleView = mView.findViewById(R.id.playerRecyclerView);
    }


    private void getPlayers() {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            CommonUtils.getInstance().showRedToast(getString(R.string.no_internet), getActivity(), 112);
            return;
        }

        NetworkCallback<PlayersResponse> networkCallback = new NetworkCallback<PlayersResponse>() {
            @Override
            public void onSuccess(PlayersResponse response) {
                AppLogger.d("response", "" + response.toString());
            }

            @Override
            public void onError(VolleyError error) {
            }
        };
        Map<String, String> params = new HashMap<>();
        NetworkCall<PlayersResponse> networkCall = new NetworkCall<>(AppConstants.BASE_URL, new HashMap<String, String>(), Request.Method.GET, networkCallback, PlayersResponse.class);
        networkCall.initiateCall("Getting players...", getActivity().getSupportFragmentManager());
    }
}
