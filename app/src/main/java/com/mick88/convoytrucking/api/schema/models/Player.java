package com.mick88.convoytrucking.api.schema.models;

import com.google.gson.annotations.SerializedName;
import com.mick88.convoytrucking.utils.MiscUtils;

/**
 * Created by Michal on 27/11/2015.
 */
public class Player extends SimplePlayer {
    public static final int
        MOD_PLAYER = 0,
        MOD_JRMOD = 1,
        MOD_MODERATOR = 2,
        MOD_ADMINISTRATOR = 3;

    int score, wanted, fines, mod, achievements, vip;
    @SerializedName("last_seen")
    int lastSeen;
    @SerializedName("convoy_score")
    int convoyScore;
    @SerializedName("custom_rank")
    String customRank;
    @SerializedName("welcome_message")
    String welcomeMessage;
    String country;
    String police;
    PlayerStatistics statistics;

    public int getScore() {
        return score;
    }

    public int getWanted() {
        return wanted;
    }

    public int getFines() {
        return fines;
    }

    public int getMod() {
        return mod;
    }

    public int getAchievements() {
        return achievements;
    }

    public int getVip() {
        return vip;
    }

    public int getLastSeen() {
        return lastSeen;
    }

    public int getConvoyScore() {
        return convoyScore;
    }

    public String getCustomRank() {
        return customRank;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getCountry() {
        return country;
    }

    public String getPolice() {
        return police;
    }

    public PlayerStatistics getStatistics() {
        return statistics;
    }

    public boolean isStaff() {
        return getMod() > MOD_PLAYER;
    }

    public boolean isVip() {
        return getVip() >= MiscUtils.getTimestamp();
    }
}
