package com.mick88.convoytrucking.utils;

/**
 * Created by Michal on 29/11/2015.
 */
public class MiscUtils {
    /**
     * Converts ms to seconds
     */
    public static int msToSeconds(long ms) {
        return (int) (ms / 1000L);
    }

    /**
     * Current time as unix timestamp
     */
    public static int getTimestamp() {
        return msToSeconds(System.currentTimeMillis());
    }
}
