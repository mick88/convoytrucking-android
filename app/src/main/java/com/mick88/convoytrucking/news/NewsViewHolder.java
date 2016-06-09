package com.mick88.convoytrucking.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mick88.convoytrucking.R;

/**
 * Created by Michal on 09/06/2016.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder {
    TextView tvTitle, tvContent;

    public NewsViewHolder(View view) {
        super(view);
        this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        this.tvContent = (TextView) view.findViewById(R.id.tvContent);
    }
}
