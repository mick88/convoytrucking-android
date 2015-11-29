package com.mick88.convoytrucking.dealers;

import android.os.Bundle;

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
}
