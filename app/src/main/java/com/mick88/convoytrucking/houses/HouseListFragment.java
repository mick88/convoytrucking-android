package com.mick88.convoytrucking.houses;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.HouseFeed;
import com.mick88.convoytrucking.api.schema.models.House;
import com.mick88.convoytrucking.base.ApiFragment;
import com.mick88.convoytrucking.houses.detail.HouseActivity;

/**
 * Created by Michal on 25/11/2015.
 */
public class HouseListFragment extends ApiFragment<HouseFeed> implements AdapterView.OnItemClickListener {
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onResponse(HouseFeed response) {
        super.onResponse(response);
        final ArrayAdapter<House> houseAdapter = new HouseAdapter(getContext(), response.getResults());

        final ListView listView = (ListView) getView().findViewById(R.id.listView);
        listView.setAdapter(houseAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final House house = (House) parent.getItemAtPosition(position);
        Intent intent = HouseActivity.newIntent(getContext(), house.getId());
        startActivity(intent);
    }
}
