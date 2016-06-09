package com.mick88.convoytrucking.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mick88.convoytrucking.api.VolleySingleton;

/**
 * Created by Michal on 03/11/2015.
 */
public class BaseFragment extends Fragment implements Response.ErrorListener {
    protected void sendRequest(Request<?> request) {
        if (isAdded() == false) throw new IllegalStateException("Fragment is not attached");
        request.setTag(this);
        VolleySingleton.getInstance(getContext()).getRequestQueue().add(request);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        VolleySingleton.getInstance(getContext()).getRequestQueue().cancelAll(this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
