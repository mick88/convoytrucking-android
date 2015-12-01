package com.mick88.convoytrucking.vehicles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.VolleySingleton;
import com.mick88.convoytrucking.api.schema.models.Vehicle;
import com.mick88.convoytrucking.utils.BaseAdapter;
import com.mick88.convoytrucking.utils.FormatUtils;

import java.util.Locale;

/**
 * Created by Michal on 29/11/2015.
 */
public class VehicleAdapter extends BaseAdapter<Vehicle, VehicleViewHolder> {
    protected final Context context;
    protected final ImageLoader imageLoader;

    public VehicleAdapter(Context context, Vehicle[] vehicles) {
        super(vehicles);
        this.context = context;
        this.imageLoader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    protected VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater layoutInflater) {
        final View view = layoutInflater.inflate(R.layout.card_vehicle, parent, false);
        return new VehicleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleViewHolder holder, int position) {
        final Vehicle vehicle = getItem(position);

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
}
