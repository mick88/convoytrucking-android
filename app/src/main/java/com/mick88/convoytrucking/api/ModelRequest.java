package com.mick88.convoytrucking.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by Michal on 03/11/2015.
 */
public class ModelRequest<T> extends Request<T> {
    public final Gson gson = new Gson();
    @NonNull
    protected final Class<T> clazz;
    @Nullable
    protected final Response.Listener<T> listener;

    public ModelRequest(int method, String url, @NonNull Class<T> clazz, Response.ErrorListener errorListener, @Nullable Response.Listener<T> responseListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = responseListener;
    }

    public ModelRequest(String url, @NonNull Class<T> clazz, Response.ErrorListener errorListener, @Nullable Response.Listener<T> responseListener) {
        this(Method.GET, url, clazz, errorListener, responseListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            final T object = getResponseObject(response);
            return Response.success(object, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }

    private T getResponseObject(NetworkResponse response) throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream(response.data);
        final String charset = HttpHeaderParser.parseCharset(response.headers, ApiConstants.ENCODING);
        InputStreamReader reader = new InputStreamReader(stream, charset);
        T object = gson.fromJson(reader, clazz);
        reader.close();
        return object;
    }

    @Override
    protected void deliverResponse(T response) {
        if (listener != null) listener.onResponse(response);
    }
}
