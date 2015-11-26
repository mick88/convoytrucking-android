package com.mick88.convoytrucking.houses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.schema.models.House;
import com.mick88.convoytrucking.houses.detail.HouseActivity;

/**
 * Created by Michal on 26/11/2015.
 */
public class HouseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    House currentItem;
    final Context context;
    final NetworkImageView imageView;
    final TextView tvAddress;
    final TextView tvPrice, tvSlots, tvOwner;
    final ImageView imgIcon;

    public HouseViewHolder(View view) {
        super(view);
        this.imageView = (NetworkImageView) view.findViewById(R.id.image);
        this.tvAddress = (TextView) view.findViewById(R.id.tvAddress);
        this.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        this.tvSlots = (TextView) view.findViewById(R.id.tvSlots);
        this.tvOwner = (TextView) view.findViewById(R.id.tvOwner);
        this.imgIcon = (ImageView) view.findViewById(R.id.icon);

        this.context = view.getContext();
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (currentItem != null) {
            Intent intent = HouseActivity.newIntent(context, currentItem.getId());
            context.startActivity(intent);
        }
    }
}
