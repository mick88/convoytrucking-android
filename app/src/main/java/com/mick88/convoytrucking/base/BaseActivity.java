package com.mick88.convoytrucking.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.VolleySingleton;

/**
 * Created by Michal on 03/11/2015.
 */
public abstract class BaseActivity extends AppCompatActivity implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, R.string.error_request, Toast.LENGTH_SHORT).show();
    }

    protected <T> void sendRequest(Request<T> request) {
        request.setTag(this);
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VolleySingleton.getInstance(this).getRequestQueue().cancelAll(this);
    }

    /**
     * Setup toolbar
     * needs to be invoked onCreate for activities which use the toolbar
     *
     * @return the toolbar instance
     */
    protected Toolbar initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }
}
