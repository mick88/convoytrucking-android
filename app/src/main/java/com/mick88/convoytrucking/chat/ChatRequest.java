package com.mick88.convoytrucking.chat;

import android.support.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.ChatFeed;
import com.mick88.convoytrucking.api.schema.models.ChatMessage;

import java.io.IOException;

/**
 * Created by Michal on 03/11/2015.
 */
public class ChatRequest extends ModelRequest<ChatFeed> {
    public ChatRequest(Response.ErrorListener errorListener, @Nullable Response.Listener<ChatFeed> responseListener) {
        super(ApiConstants.API_CHAT, ChatFeed.class, errorListener, responseListener);
    }

    @Override
    protected ChatFeed parseJson(NetworkResponse response) throws IOException {
        final ChatFeed chatFeed = super.parseJson(response);
        // reverse order of messages
        final ChatMessage[] messages = chatFeed.getResults();
        for (int i = 0; i < messages.length / 2; i++) {
            ChatMessage temp = messages[i];
            messages[i] = messages[messages.length - i - 1];
            messages[messages.length - i - 1] = temp;
        }
        return chatFeed;
    }
}
