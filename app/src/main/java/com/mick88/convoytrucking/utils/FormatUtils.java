package com.mick88.convoytrucking.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Michal on 25/11/2015.
 */
public class FormatUtils {
    /**
     * Formats integer price
     *
     * @return formatted string with $ sign
     */
    public static String formatPrice(int price) {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.US);
        return String.format("$%s", format.format(price));
    }
}
