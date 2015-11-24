package com.mick88.convoytrucking.chat;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.ChatFeed;
import com.mick88.convoytrucking.api.schema.models.ChatMessage;
import com.mick88.convoytrucking.base.ApiFragment;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Michal on 03/11/2015.
 */
public class ChatFragment extends ApiFragment<ChatFeed> {

    // how often chat will refresh
    public static final long REFRESH_INTERVAL = 1500L;
    private Timer refreshTimer;

    @Override
    protected Class<ChatFeed> getModelClass() {
        return ChatFeed.class;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scheduleRefresh();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    void scheduleRefresh() {
        refreshTimer = new Timer();
        final Handler uiHandler = new Handler();
        refreshTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                uiHandler.post(new TimerTask() {
                    @Override
                    public void run() {
                        if (isRequestPending() == false) {
                            sendRequest();
                        }
                    }
                });
            }
        }, REFRESH_INTERVAL, REFRESH_INTERVAL);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        refreshTimer.cancel();
        refreshTimer = null;
    }

    @Override
    public void onResponse(ChatFeed response) {
        super.onResponse(response);
        ArrayAdapter<ChatMessage> adapter = new ChatAdapter(getContext(), response.getResults());
        ListView listView = (ListView) getView().findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setSelection(adapter.getCount() - 1);
    }

    @NonNull
    @Override
    protected ModelRequest<ChatFeed> createRequest() {
        return new ChatRequest(this, this);
    }
}
