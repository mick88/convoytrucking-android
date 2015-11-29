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
        final String number = formatLargeNumber(price);
        return String.format("$%s", number);
    }

    /**
     * Adds comma separators to the number
     */
    public static String formatLargeNumber(int number) {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());
        return format.format(number);
    }

    /**
     * converts timestamp to time delta in days to display to user
     * @param timestamp unix timestamp
     * @return string "{days} days ago"
     */
    public static String formatTimeAgo(int timestamp) {
        final int seconds = MiscUtils.getTimestamp() - timestamp;
        final int days = seconds / 60 / 60 / 24;
        return String.format(Locale.ENGLISH, "%d days ago", days);
    }
}
