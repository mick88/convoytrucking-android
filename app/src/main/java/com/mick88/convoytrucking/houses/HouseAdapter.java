package com.mick88.convoytrucking.houses;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.VolleySingleton;
import com.mick88.convoytrucking.api.schema.models.House;
import com.mick88.convoytrucking.utils.FormatUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Michal on 25/11/2015.
 */
public class HouseAdapter extends RecyclerView.Adapter<HouseViewHolder> {
    protected final List<House> houses;
    protected final ImageLoader imageLoader;
    protected final Context context;

    public HouseAdapter(Context context, House[] houses) {
        super();
        final List<House> houseList = Arrays.asList(houses);
        this.houses = new ArrayList<>(houseList);
        this.context = context;
        imageLoader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public HouseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_house, parent, false);
        return new HouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HouseViewHolder holder, int position) {
        final House house = houses.get(position);

        holder.currentItem = house;

        holder.imageView.setDefaultImageResId(R.drawable.placeholder_house);
        holder.imageView.setImageUrl(house.getImageUrl(House.IMAGE_SIZE_MEDIUM), imageLoader);
        holder.tvAddress.setText(house.getAddress());
        holder.tvPrice.setText(FormatUtils.formatPrice(house.getPrice()));
        holder.tvSlots.setText(context.getString(R.string.s_slots, house.getParking()));
        if (house.isForSale()) {
            holder.imgIcon.setImageResource(R.drawable.ic_house_forsale);
            holder.tvOwner.setText(null);
        } else {
            holder.imgIcon.setImageResource(R.drawable.ic_house_owned);
            holder.tvOwner.setText(house.getOwner().getName());
        }
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public void addHouses(Collection<House> newHouses) {
        int start = this.houses.size();
        this.houses.addAll(newHouses);
        notifyItemRangeInserted(start, newHouses.size());
    }
}
