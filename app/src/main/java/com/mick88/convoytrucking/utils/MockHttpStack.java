package com.mick88.convoytrucking.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import com.mick88.convoytrucking.BuildConfig;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Michal on 06/02/2016.
 */
public class MockHttpStack implements HttpStack {
    public static final String TAG = "MockHttpStack";
    private final static Map<String, String> overrideUrls = new ArrayMap<>();
    private final AssetManager assetManager;

    static {

    }

    public MockHttpStack(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public MockHttpStack(Context context) {
        this(context.getAssets());
    }

    String convertUrlToPath(String url) {
        if (overrideUrls.containsKey(url)) {
            return overrideUrls.get(url);
        } else {
            // remove unsupported characters and append .json extension
            return url
                    .toLowerCase(Locale.ENGLISH)
                    .replaceAll("[^a-z0-9./-=_]+", "-")
                    .replaceAll("/+$", "") + ".json";
        }
    }

    HttpEntity createHttpEntity(String url) throws IOException {
        // trim base address from the url
        url = url.substring(BuildConfig.BASE_URL.length());
        final String path = convertUrlToPath(url);

        Log.d(TAG, String.format(Locale.ENGLISH, "Serving file %s for url /%s", path, url));

        BasicHttpEntity entity = new BasicHttpEntity();
        entity.setContent(assetManager.open(path));
        return entity;
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        BasicHttpResponse response = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK"));
        response.setEntity(createHttpEntity(request.getUrl()));
        return response;
    }
}
