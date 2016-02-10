package com.mick88.convoytrucking;

import android.support.test.espresso.IdlingResource;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Set;

public class VolleyIdlingResource implements IdlingResource {
    private static final String TAG = "VolleyIdlingResource";

    // written from main thread, read from any thread.
    private volatile ResourceCallback resourceCallback;

    private Field currentRequestsField;
    private RequestQueue requestQueue;

    public VolleyIdlingResource(RequestQueue requestQueue) throws SecurityException, NoSuchFieldException {
        this.requestQueue = requestQueue;
        currentRequestsField = RequestQueue.class.getDeclaredField("mCurrentRequests");
        currentRequestsField.setAccessible(true);
    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isIdleNow() {
        try {
            @SuppressWarnings("unchecked")
            Collection<Request> set = (Set<Request>) currentRequestsField.get(requestQueue);
            int count = set.size();

            if (count == 0) {
                Log.d(TAG, "Volley is idle now! with: " + count);
                resourceCallback.onTransitionToIdle();
                return true;
            } else {
                Log.d(TAG, "Not idle... " + count);
                return false;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

}