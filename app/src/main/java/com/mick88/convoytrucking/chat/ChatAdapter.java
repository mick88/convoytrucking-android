package com.mick88.convoytrucking.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.schema.models.ChatMessage;

/**
 * Created by Michal on 03/11/2015.
 */
public class ChatAdapter extends ArrayAdapter<ChatMessage> {
    public ChatAdapter(Context context, ChatMessage[] chatMessages) {
        super(context, R.layout.item_chat_message, chatMessages);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ChatMessage item = getItem(position);
        TextView textView = (TextView) super.getView(position, view, parent);
        if (item.isSystemMessage()) textView.setText(item.getText());
        else textView.setText(item.toString());
        return textView;
    }
}
