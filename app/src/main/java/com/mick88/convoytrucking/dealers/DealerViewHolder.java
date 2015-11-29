package com.mick88.convoytrucking.dealers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.mick88.convoytrucking.R;

/**
 * Created by Michal on 29/11/2015.
 */
public class DealerViewHolder extends RecyclerView.ViewHolder {
    final NetworkImageView imageView;
    final TextView tvName, tvNumVehicles;


    public DealerViewHolder(View itemView) {
        super(itemView);
        this.imageView = (NetworkImageView) itemView.findViewById(R.id.image);
        this.tvName = (TextView) itemView.findViewById(R.id.tvName);
        this.tvNumVehicles = (TextView) itemView.findViewById(R.id.tvNumVehicles);
    }
}
