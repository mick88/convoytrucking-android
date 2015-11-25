package com.mick88.convoytrucking.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.mick88.convoytrucking.utils.BitmapCache;

/**
 * Created by lduffy on 17/04/15.
 */
public class VolleySingleton {
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private Context context;
    private ImageLoader.ImageCache imageCache;
    private ImageLoader imageLoader;

    private VolleySingleton(Context context) {
        this.context = context.getApplicationContext();
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader.ImageCache getImageCache() {
        if (imageCache == null)
            imageCache = new BitmapCache();
        return imageCache;
    }

    public ImageLoader getImageLoader() {
        if (imageLoader == null) {
            imageLoader = new ImageLoader(getRequestQueue(), getImageCache());
        }
        return imageLoader;
    }
}