package com.mick88.convoytrucking.chat;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.schema.feeds.ChatFeed;
import com.mick88.convoytrucking.api.schema.models.ChatMessage;
import com.mick88.convoytrucking.base.ApiFragment;

/**
 * Created by Michal on 03/11/2015.
 */
public class ChatFragment extends ApiFragment<ChatFeed> {

    @Override
    protected Class<ChatFeed> getModelClass() {
        return ChatFeed.class;
    }

    @Override
    public void onResponse(ChatFeed response) {
        super.onResponse(response);
        ArrayAdapter<ChatMessage> adapter = new ChatAdapter(getContext(), response.getResults());
        ListView listView = (ListView) getView().findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
