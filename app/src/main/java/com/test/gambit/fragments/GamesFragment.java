package com.test.gambit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.test.gambit.R;
import com.test.gambit.model.GameResponse;
import com.test.gambit.utils.AppConstants;
import com.test.gambit.utils.CommonUtils;
import com.test.gambit.utils.NetworkUtils;
import com.test.gambit.views.HomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.myvolley.NetworkCall;
import in.myvolley.interfaces.NetworkCallback;

/**
 * Created by sunilparmar on 4/27/2019.
 */

public class GamesFragment extends Fragment {
    private View mView;
    private RecyclerView mGameRecycleView;
    private GameAdapter mAdapter;
    private ArrayList<GameResponse.Data> gameArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_games, container, false);
        initViews();
        gameArrayList.clear();
        initAdapter();
        getGame();
        return mView;
    }

    private void initViews() {
        mGameRecycleView = mView.findViewById(R.id.gameRecyclerView);
    }

    private void initAdapter() {
        mAdapter = new GameAdapter();
        mGameRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mGameRecycleView.setAdapter(mAdapter);

    }

    private void getGame() {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            CommonUtils.getInstance().showRedToast(getString(R.string.no_internet), getActivity(), 112);
            return;
        }

        NetworkCallback<GameResponse> networkCallback = new NetworkCallback<GameResponse>() {
            @Override
            public void onSuccess(GameResponse response) {

                parseData(response);
            }

            @Override
            public void onError(VolleyError error) {
            }
        };
        Map<String, String> params = new HashMap<>();
        NetworkCall<GameResponse> networkCall = new NetworkCall<>(AppConstants.GAMES_URL, new HashMap<String, String>(), Request.Method.GET, networkCallback, GameResponse.class);
        networkCall.initiateCall();
    }

    private void parseData(GameResponse response) {
        if (response.getData().size() > 0) {
            gameArrayList.addAll(response.getData());
            mAdapter.notifyDataSetChanged();
            updateTabText();

        }
    }

    class GameAdapter extends RecyclerView.Adapter<GameAdapter.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView homeTeamAbbreviationTextView, visitorTeamAbbreviationTextView,
                    homeTeamFullNameTextView, visitorTeamFullNameíTextView;

            public MyViewHolder(View view) {
                super(view);
                homeTeamAbbreviationTextView = view.findViewById(R.id.homeTeamAbbreviationTextView);
                visitorTeamAbbreviationTextView = view.findViewById(R.id.visitorTeamAbbreviationTextView);
                homeTeamFullNameTextView = view.findViewById(R.id.homeTeamFullNameTextView);
                visitorTeamFullNameíTextView = view.findViewById(R.id.visitorTeamFullNameíTextView);
            }
        }


        public GameAdapter() {

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_game, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            GameResponse.Data dataModel = gameArrayList.get(position);

            holder.homeTeamAbbreviationTextView.setText(dataModel.getHome_team().getAbbreviation());
            holder.visitorTeamAbbreviationTextView.setText(dataModel.getVisitor_team().getAbbreviation());
            holder.homeTeamFullNameTextView.setText(dataModel.getHome_team().getName());
            holder.visitorTeamFullNameíTextView.setText(dataModel.getVisitor_team().getName());
        }

        @Override
        public int getItemCount() {
            return gameArrayList.size();
        }
    }

    public void updateTabText() {
        try {

            View view = ((HomeActivity) getActivity()).mTabLayout.getTabAt(1).getCustomView();
            View badgeContainer = view.findViewById(R.id.badgeCotainer);
            TextView countTextView = view.findViewById(R.id.badge);
            if (gameArrayList.size() > 0) {
                badgeContainer.setVisibility(View.VISIBLE);
                countTextView.setText(gameArrayList.size() + "");
            } else {
                badgeContainer.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
