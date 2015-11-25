package com.mick88.convoytrucking.houses;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.HouseFeed;
import com.mick88.convoytrucking.api.schema.models.House;
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
        final ArrayAdapter<House> houseAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, response.getResults());

        final ListView listView = (ListView) getView().findViewById(R.id.listView);
        listView.setAdapter(houseAdapter);
    }
}
