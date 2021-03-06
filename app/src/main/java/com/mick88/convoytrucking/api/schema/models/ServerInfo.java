package com.mick88.convoytrucking.api.schema.models;

import com.google.gson.annotations.SerializedName;

import java.util.Locale;

/**
 * Created by Michal on 21/11/2015.
 */
public class ServerInfo {
    public static final String
        SERVER_STATUS_LOCKED = "locked",
        SERVER_STATUS_ONLINE = "online",
        SERVER_STATUS_OFFLINE = "offline";
    @SerializedName("samp_address")
    String sampAddress;
    @SerializedName("samp_port")
    int sampPort;
    @SerializedName("server_status")
    String serverStatus;
    @SerializedName("num_players")
    Integer numPlayers;
    @SerializedName("max_players")
    Integer maxPlayers;
    @SerializedName("gamemode")
    String gamemode;
    @SerializedName("last_restart")
    int lastRestart;

    public String getSampAddress() {
        return sampAddress;
    }

    public int getSampPort() {
        return sampPort;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public String getGamemode() {
        return gamemode;
    }

    public int getLastRestart() {
        return lastRestart;
    }

    public boolean isOnline() {
        return SERVER_STATUS_ONLINE.equals(this.serverStatus);
    }

    @Override
    public String toString() {
        if (isOnline()) {
            return String.format(Locale.ENGLISH, "%s: %s/%s", gamemode, numPlayers, maxPlayers);
        } else {
            return this.serverStatus;
        }
    }

    public int getUptime() {
        return (int)(System.currentTimeMillis() / 1000l) - lastRestart;
    }
}
