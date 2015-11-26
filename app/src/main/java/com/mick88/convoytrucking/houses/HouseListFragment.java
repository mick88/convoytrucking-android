package com.mick88.convoytrucking.houses;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.HouseFeed;
import com.mick88.convoytrucking.base.ApiFragment;

/**
 * Created by Michal on 25/11/2015.
 */
public class HouseListFragment extends ApiFragment<HouseFeed> {
    @Override
    protected Class<HouseFeed> getModelClass() {
        return HouseFeed.class;
    }

    @NonNull
    @Override
    protected ModelRequest<HouseFeed> createRequest() {
        return new HousesRequest(true, this, this);
    }

    @Override
    public void onResponse(HouseFeed response) {
        super.onResponse(response);
        final HouseAdapter houseAdapter = new HouseAdapter(getContext(), response.getResults());
        final RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(houseAdapter);
    }
}
