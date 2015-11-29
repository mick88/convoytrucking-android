package com.mick88.convoytrucking.dealers.vehicles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.android.volley.Request;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.VehicleFeed;
import com.mick88.convoytrucking.api.schema.models.Dealer;
import com.mick88.convoytrucking.vehicles.VehicleListActivity;

import java.util.Locale;

/**
 * Created by Michal on 29/11/2015.
 */
public class DealerVehicleListActivity extends VehicleListActivity {
    public static final String EXTRA_DEALER = "dealer";

    @Override
    protected Toolbar initToolbar() {
        final Toolbar toolbar = super.initToolbar();
        Dealer dealer = getIntent().getParcelableExtra(EXTRA_DEALER);
        setTitle(dealer.getName());
        toolbar.setSubtitle(getString(R.string.num_vehicles, dealer.getOfferedModels().length));
        return toolbar;
    }

    @Override
    protected void downloadVehicles() {
        Dealer dealer = getIntent().getParcelableExtra(EXTRA_DEALER);
        final int[] offeredModels = dealer.getOfferedModels();
        String[] ids = new String[offeredModels.length];
        for (int i = 0; i < offeredModels.length; i++) {
            ids[i] = String.valueOf(offeredModels[i]);
        }
        final String url = String.format(Locale.ENGLISH, "%s?modelid__in=%s", ApiConstants.API_VEHICLES, TextUtils.join(",", ids));

        Request<VehicleFeed> request = new ModelRequest<>(url, VehicleFeed.class, this, this);
        sendRequest(request);
    }

    public static Intent createIntent(Context context, Dealer dealer) {
        if (dealer == null) throw new NullPointerException("Dealer is null");

        final Intent intent = new Intent(context, DealerVehicleListActivity.class);
        intent.putExtra(EXTRA_DEALER, dealer);
        return intent;
    }
}
