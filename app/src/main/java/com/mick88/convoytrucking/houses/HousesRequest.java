package com.mick88.convoytrucking.houses;

import android.support.annotation.Nullable;

import com.android.volley.Response;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.HouseFeed;

/**
 * Created by Michal on 25/11/2015.
 */
public class HousesRequest extends ModelRequest<HouseFeed> {
    public HousesRequest(String url, Response.ErrorListener errorListener, @Nullable Response.Listener<HouseFeed> responseListener) {
        super(url, HouseFeed.class, errorListener, responseListener);
    }

    public HousesRequest(boolean forSale, Response.ErrorListener errorListener, @Nullable Response.Listener<HouseFeed> responseListener) {
        this(buildUrl(forSale), errorListener, responseListener);
    }

    protected static String buildUrl(boolean forSale) {
        if (forSale) {
            return ApiConstants.API_HOUSES_FORSALE;
        } else {
            return ApiConstants.API_HOUSES;
        }
    }
}
