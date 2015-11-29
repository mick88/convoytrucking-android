package com.mick88.convoytrucking.dealers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.VolleySingleton;
import com.mick88.convoytrucking.api.schema.models.Dealer;

/**
 * Created by Michal on 29/11/2015.
 */
public class DealerAdapter extends RecyclerView.Adapter<DealerViewHolder> {
    final Dealer[] dealers;
    protected final ImageLoader imageLoader;
    protected final Context context;

    public DealerAdapter(Context context, Dealer[] dealers) {
        this.dealers = dealers;
        this.context = context;
        imageLoader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public DealerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dealer, parent, false);
        return new DealerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DealerViewHolder holder, int position) {
        final Dealer dealer = dealers[position];
        holder.imageView.setImageUrl(dealer.getImageUrl(), imageLoader);
        holder.tvName.setText(dealer.getName());
        final String numVehicles = context.getString(R.string.num_vehicles, dealer.getOfferedModels().length);
        holder.tvNumVehicles.setText(numVehicles);
    }

    @Override
    public int getItemCount() {
        return dealers.length;
    }
}
