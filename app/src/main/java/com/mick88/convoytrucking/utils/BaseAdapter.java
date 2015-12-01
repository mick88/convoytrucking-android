package com.mick88.convoytrucking.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michal on 01/12/2015.
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected final List<T> items;

    public BaseAdapter(List<T> items) {
        if (items == null) throw new NullPointerException();
        this.items = items;
    }

    public BaseAdapter(T[] items) {
        this(new ArrayList<T>(Arrays.asList(items)));
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder(parent, viewType, LayoutInflater.from(parent.getContext()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    protected abstract VH onCreateViewHolder(ViewGroup parent, int viewType, LayoutInflater layoutInflater);

    public void addItems(List<T> newItems) {
        final int start = items.size();
        items.addAll(newItems);
        notifyItemRangeInserted(start, newItems.size());
    }
}
