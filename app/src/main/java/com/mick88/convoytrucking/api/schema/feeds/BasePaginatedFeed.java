package com.mick88.convoytrucking.api.schema.feeds;

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
}