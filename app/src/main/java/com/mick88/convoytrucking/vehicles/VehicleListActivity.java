package com.mick88.convoytrucking.vehicles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.VehicleFeed;
import com.mick88.convoytrucking.base.BaseActivity;

/**
 * Created by Michal on 29/11/2015.
 */
public class VehicleListActivity extends BaseActivity implements Response.Listener<VehicleFeed> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initToolbar();

        downloadVehicles();
    }

    protected void downloadVehicles() {
        Request<VehicleFeed> request = new ModelRequest<>(ApiConstants.API_VEHICLES, VehicleFeed.class, this, this);
        sendRequest(request);
    }

    @Override
    protected Toolbar initToolbar() {
        final Toolbar toolbar = super.initToolbar();
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        return toolbar;
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, VehicleListActivity.class);
    }

    @Override
    public void onResponse(VehicleFeed response) {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final int numColumns = getResources().getInteger(R.integer.grid_columns);
        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, numColumns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new VehicleAdapter(this, response.getResults()));
    }
}
