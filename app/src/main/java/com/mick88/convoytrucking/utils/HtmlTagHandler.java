package com.mick88.convoytrucking.utils;

import android.text.Editable;
import android.text.Html;

import org.xml.sax.XMLReader;

/**
 * Created by Michal on 09/06/2016.
 */
public class HtmlTagHandler implements Html.TagHandler {
    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (opening && tag.equals("li")) {
            output.append("\n \u25cf ");
        }
    }
}
