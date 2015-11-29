package com.mick88.convoytrucking.dealers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.schema.models.Dealer;
import com.mick88.convoytrucking.dealers.vehicles.DealerVehicleListActivity;

/**
 * Created by Michal on 29/11/2015.
 */
public class DealerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public Dealer currentItem;
    final NetworkImageView imageView;
    final TextView tvName, tvNumVehicles;


    public DealerViewHolder(View itemView) {
        super(itemView);
        this.imageView = (NetworkImageView) itemView.findViewById(R.id.image);
        this.tvName = (TextView) itemView.findViewById(R.id.tvName);
        this.tvNumVehicles = (TextView) itemView.findViewById(R.id.tvNumVehicles);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (currentItem != null) {
            final Context context = v.getContext();
            Intent intent = DealerVehicleListActivity.createIntent(context, currentItem);
            context.startActivity(intent);
        }
    }
}
