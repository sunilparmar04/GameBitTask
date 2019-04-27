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
import com.test.gambit.model.PlayersResponse;
import com.test.gambit.utils.AppConstants;
import com.test.gambit.utils.CommonUtils;
import com.test.gambit.utils.NetworkUtils;

import java.util.ArrayList;
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
    private PlayerAdapter mAdapter;

    private ArrayList<PlayersResponse.Data> playerArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_players, container, false);
        initViews();
        playerArrayList.clear();
        initAdapter();
        getPlayers();
        return mView;
    }

    private void initViews() {
        mPlayerRecycleView = mView.findViewById(R.id.playerRecyclerView);
    }

    private void initAdapter() {
        mAdapter = new PlayerAdapter();
        mPlayerRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPlayerRecycleView.setAdapter(mAdapter);
    }

    private void getPlayers() {
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            CommonUtils.getInstance().showRedToast(getString(R.string.no_internet), getActivity(), 112);
            return;
        }

        NetworkCallback<PlayersResponse> networkCallback = new NetworkCallback<PlayersResponse>() {
            @Override
            public void onSuccess(PlayersResponse response) {

                parseData(response);
            }

            @Override
            public void onError(VolleyError error) {
            }
        };
        Map<String, String> params = new HashMap<>();
        NetworkCall<PlayersResponse> networkCall = new NetworkCall<>(AppConstants.PlAYERS_URL, new HashMap<String, String>(), Request.Method.GET, networkCallback, PlayersResponse.class);
        networkCall.initiateCall("Getting players...", getActivity().getSupportFragmentManager());
    }

    private void parseData(PlayersResponse response) {
        playerArrayList.addAll(response.getData());

        if (playerArrayList.size() > 0) {
            mAdapter.notifyDataSetChanged();
        }
    }

    class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> {


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView firstNameTextView, teamNameTextView, lastNameTextView, teamIdTextView,
                    positionTextView, fullNameTextView, conferenceDivisionTextView;

            public MyViewHolder(View view) {
                super(view);
                firstNameTextView = view.findViewById(R.id.firstNameTextView);
                teamNameTextView = view.findViewById(R.id.teamNameTextView);
                lastNameTextView = view.findViewById(R.id.lastNameTextView);
                teamIdTextView = view.findViewById(R.id.teamIdTextView);
                positionTextView = view.findViewById(R.id.positionTextView);
                fullNameTextView = view.findViewById(R.id.fullNameTextView);
                conferenceDivisionTextView = view.findViewById(R.id.conferenceTextView);
            }

        }


        public PlayerAdapter() {

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_player, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            PlayersResponse.Data dataModel = playerArrayList.get(position);
            PlayersResponse.Team team = dataModel.getTeam();

            if (dataModel.getFirstName() != null) {
                holder.firstNameTextView.setText(dataModel.getFirstName());
            } else {
                holder.firstNameTextView.setText("DummyName");
            }
            if (dataModel.getLastName() != null) {
                holder.lastNameTextView.setText(dataModel.getLastName());
            } else {
                holder.lastNameTextView.setText("DummyLastName");
            }

            if (team.getName() != null) {
                holder.teamNameTextView.setText(team.getAbbreviation());
            } else {
                holder.teamNameTextView.setText("Dummy team");
            }
            holder.teamIdTextView.setText("# " + dataModel.getTeam().getId());
            if (dataModel.getPosition() != null) {
                holder.positionTextView.setText("Position - " + dataModel.getPosition());
            } else {
                holder.positionTextView.setText("Position - DummyE");
            }

            if (team.getFullName() != null) {
                holder.fullNameTextView.setText(team.getName());
            } else {
                holder.fullNameTextView.setText("dummy");
            }

            if (team.getConference() != null &&
                    team.getDivision() != null) {
                holder.conferenceDivisionTextView.setText(team.getConference() + " / " + team.getDivision());

            } else if (team.getConference() != null) {
                holder.conferenceDivisionTextView.setText(team.getConference());

            } else if (team.getDivision() != null) {
                holder.conferenceDivisionTextView.setText(team.getDivision());
            } else {
                holder.conferenceDivisionTextView.setText("dummy");
            }

        }

        @Override
        public int getItemCount() {
            return playerArrayList.size();
        }
    }

}
