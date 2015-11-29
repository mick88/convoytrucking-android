package com.mick88.convoytrucking.vehicles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.mick88.convoytrucking.R;

/**
 * Created by Michal on 29/11/2015.
 */
public class VehicleViewHolder extends RecyclerView.ViewHolder {
    final NetworkImageView imageView;
    final TextView tvPrice, tvName, tvTopSpeed;

    public VehicleViewHolder(View itemView) {
        super(itemView);
        this.imageView = (NetworkImageView) itemView.findViewById(R.id.image);
        this.tvName = (TextView) itemView.findViewById(R.id.tvName);
        this.tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        this.tvTopSpeed = (TextView) itemView.findViewById(R.id.tvTopSpeed);

    }
}
