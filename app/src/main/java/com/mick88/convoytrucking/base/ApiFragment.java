package com.mick88.convoytrucking.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.BasePaginatedFeed;
import com.mick88.convoytrucking.utils.EndlessRecyclerOnScrollListener;

/**
 * Created by Michal on 03/11/2015.
 * <p/>
 * A helper fragment with boilerplate code to making requests
 */
public abstract class ApiFragment<T> extends BaseFragment implements Response.Listener<T> {

    private ModelRequest<T> currentRequest;
    protected String url = null;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sendRequest();
    }

    protected abstract Class<T> getModelClass();

    protected void sendRequest() {
        currentRequest = createRequest();
        sendRequest(currentRequest);
    }

    @NonNull
    protected ModelRequest<T> createRequest() {
        if (url == null) throw new NullPointerException("API url is not set");
        return new ModelRequest<>(url, getModelClass(), this, this);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    void initRecyclerView(RecyclerView recyclerView) {
        final int numColumns = getResources().getInteger(R.integer.grid_columns);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), numColumns);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (isRequestPending() == false) fetchMore();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_list, container, false);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        initRecyclerView(recyclerView);
        return view;
    }

    @Override
    public void onResponse(T response) {
        currentRequest = null;
        if (response instanceof BasePaginatedFeed<?>) {
            setUrl(((BasePaginatedFeed<?>) response).getNext());
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
        currentRequest = null;
    }

    protected void fetchMore() {
        if (url != null) {
            sendRequest();
        }
    }

    /**
     * Tells whether a request has been sent
     */
    public boolean isRequestPending() {
        return currentRequest != null;
    }
}
