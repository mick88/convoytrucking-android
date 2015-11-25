package com.mick88.convoytrucking.houses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.VolleySingleton;
import com.mick88.convoytrucking.api.schema.models.House;
import com.mick88.convoytrucking.utils.FormatUtils;

/**
 * Created by Michal on 25/11/2015.
 */
public class HouseAdapter extends ArrayAdapter<House> {

    private static class ViewHolder {
        NetworkImageView imageView;
        TextView tvAddress;
        TextView tvPrice;
    }

    private ImageLoader imageLoader;

    public HouseAdapter(Context context, House[] houses) {
        super(context, 0, houses);
        imageLoader = VolleySingleton.getInstance(getContext()).getImageLoader();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_house, parent, false);
            view.setTag(holder = new ViewHolder());
            holder.imageView = (NetworkImageView) view.findViewById(R.id.image);
            holder.tvAddress = (TextView) view.findViewById(R.id.tvAddress);
            holder.tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final House house = getItem(position);

        holder.imageView.setDefaultImageResId(R.drawable.placeholder_house);
        holder.imageView.setImageUrl(house.getImageUrl(House.IMAGE_SIZE_MEDIUM), imageLoader);
        holder.tvAddress.setText(house.getAddress());
        holder.tvPrice.setText(FormatUtils.formatPrice(house.getPrice()));
        return view;
    }
}
