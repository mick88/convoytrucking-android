package com.mick88.convoytrucking.houses;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.HouseFeed;
import com.mick88.convoytrucking.api.schema.models.House;
import com.mick88.convoytrucking.base.ApiFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Michal on 25/11/2015.
 */
public class HouseListFragment extends ApiFragment<HouseFeed> {

    public HouseListFragment() {
        url = ApiConstants.API_HOUSES_FORSALE;
    }

    @Override
    protected Class<HouseFeed> getModelClass() {
        return HouseFeed.class;
    }

    @NonNull
    @Override
    protected ModelRequest<HouseFeed> createRequest() {
        return new HousesRequest(this.url, this, this);
    }

    @Override
    public void onResponse(HouseFeed response) {
        super.onResponse(response);
        final RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        HouseAdapter houseAdapter = (HouseAdapter) recyclerView.getAdapter();
        if (houseAdapter == null) {
            houseAdapter = new HouseAdapter(getContext(), response.getResults());
            recyclerView.setAdapter(houseAdapter);

        } else {
            final List<House> houses = Arrays.asList(response.getResults());
            houseAdapter.addItems(houses);
        }
    }

}
