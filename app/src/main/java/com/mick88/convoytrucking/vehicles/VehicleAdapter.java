package com.mick88.convoytrucking.vehicles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.VolleySingleton;
import com.mick88.convoytrucking.api.schema.models.Vehicle;
import com.mick88.convoytrucking.utils.FormatUtils;

import java.util.Locale;

/**
 * Created by Michal on 29/11/2015.
 */
public class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder> {
    protected final Vehicle[] vehicles;
    protected final Context context;
    protected final ImageLoader imageLoader;

    public VehicleAdapter(Context context, Vehicle[] vehicles) {
        this.vehicles = vehicles;
        this.context = context;
        this.imageLoader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        final Vehicle vehicle = vehicles[position];

        holder.imageView.setImageUrl(vehicle.getImageUrl(Vehicle.VEHICLE_IMAGE_MEDIUM), imageLoader);
        holder.tvName.setText(vehicle.getName());
        final String price = FormatUtils.formatPrice(vehicle.getPrice());
        holder.tvPrice.setText(price);

        final int topSpeed = vehicle.getTopSpeed();
        if (topSpeed > 0) {
            final String topSpeedStr = String.format(Locale.ENGLISH, "%d kph", topSpeed);
            holder.tvTopSpeed.setText(context.getString(R.string.top_speed_s, topSpeedStr));
        } else {
            holder.tvTopSpeed.setText(null);
        }

    }

    @Override
    public int getItemCount() {
        return vehicles.length;
    }
}
