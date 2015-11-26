package com.mick88.convoytrucking.houses.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.VolleySingleton;
import com.mick88.convoytrucking.api.schema.models.House;
import com.mick88.convoytrucking.base.BaseActivity;
import com.mick88.convoytrucking.utils.FormatUtils;

/**
 * Created by Michal on 26/11/2015.
 */
public class HouseActivity extends BaseActivity implements Response.Listener<House> {
    public static String EXTRA_HOUSE_ID = "house_id";

    public static Intent newIntent(Context context, long houseId) {
        final Intent intent = new Intent(context, HouseActivity.class);
        intent.putExtra(EXTRA_HOUSE_ID, houseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        initToolbar();
        downloadData();
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

    protected void downloadData() {
        final long houseId = getIntent().getLongExtra(EXTRA_HOUSE_ID, 0);
        final String url = ApiConstants.API_HOUSES + houseId;
        final ModelRequest<House> request = new ModelRequest<>(url, House.class, this, this);
        sendRequest(request);
    }

    @Override
    public void onResponse(House response) {
        setData(response);
    }

    void setData(House house) {
        final NetworkImageView imageView = (NetworkImageView) findViewById(R.id.image);
        final ImageLoader imageLoader = VolleySingleton.getInstance(this).getImageLoader();
        imageView.setDefaultImageResId(R.drawable.placeholder_house);
        imageView.setImageUrl(house.getImageUrl(House.IMAGE_SIZE_MEDIUM), imageLoader);

        final TextView tvAddress = (TextView) findViewById(R.id.tvAddress);
        final TextView tvPrice = (TextView) findViewById(R.id.tvPrice);

        tvAddress.setText(house.getAddress());
        tvPrice.setText(FormatUtils.formatPrice(house.getPrice()));
    }
}
