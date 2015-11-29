package com.mick88.convoytrucking.dealers;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.schema.feeds.DealerFeed;
import com.mick88.convoytrucking.base.ApiFragment;

/**
 * Created by Michal on 29/11/2015.
 */
public class DealerListFragment extends ApiFragment<DealerFeed> {
    @Override
    protected Class<DealerFeed> getModelClass() {
        return DealerFeed.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUrl(ApiConstants.API_DEALERS);
    }

    @Override
    public void onResponse(DealerFeed response) {
        super.onResponse(response);
        final RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        final int numColumns = getResources().getInteger(R.integer.grid_columns);
        final RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), numColumns);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        DealerAdapter adapter = new DealerAdapter(getContext(), response.getResults());
        recyclerView.setAdapter(adapter);
    }

}
