package com.mick88.convoytrucking.chat;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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
    private ForegroundColorSpan
            systemMessageSpan,
            playerNameSpan;

    public ChatAdapter(Context context, ChatMessage[] chatMessages) {
        super(context, R.layout.item_chat_message, chatMessages);

        final Resources resources = context.getResources();
        systemMessageSpan = new ForegroundColorSpan(resources.getColor(R.color.system_message));
        playerNameSpan = new ForegroundColorSpan(resources.getColor(R.color.player_name));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ChatMessage item = getItem(position);
        TextView textView = (TextView) super.getView(position, view, parent);
        textView.setText(getMessageText(item));
        return textView;
    }

    protected CharSequence getMessageText(ChatMessage message) {
        if (message.isSystemMessage()) {
            final SpannableString string = new SpannableString(message.getText());
            string.setSpan(systemMessageSpan, 0, string.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
            return string;
        } else {
            final SpannableString string = new SpannableString(message.toString());
            string.setSpan(playerNameSpan, 0, message.getSender().length() + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            return string;
        }
    }
}
