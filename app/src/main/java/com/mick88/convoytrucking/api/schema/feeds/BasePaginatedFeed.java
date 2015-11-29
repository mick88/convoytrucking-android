package com.mick88.convoytrucking.api.schema.feeds;

import android.text.TextUtils;

/**
 * Created by Michal on 03/11/2015.
 */
public abstract class BasePaginatedFeed<T> {
    int count;
    String next, previous;
    T[] results;

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public T[] getResults() {
        return results;
    }

    @Override
    public String toString() {
        return TextUtils.join(", ", results);
    }
}
