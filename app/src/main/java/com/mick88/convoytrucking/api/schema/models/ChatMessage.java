package com.mick88.convoytrucking.api.schema.models;

import android.text.TextUtils;

import java.util.Locale;

/**
 * Created by Michal on 03/11/2015.
 */
public class ChatMessage extends BaseModel {
    String sender;
    String text;
    String time;

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%s: %s", sender, text);
    }

    public boolean isSystemMessage() {
        return TextUtils.isEmpty(this.sender);
    }
}
