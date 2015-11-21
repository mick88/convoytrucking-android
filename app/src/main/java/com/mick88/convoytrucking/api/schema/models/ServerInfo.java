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
    String sampPort;
    @SerializedName("server_status")
    String serverStatus;
    @SerializedName("num_players")
    String numPlayers;
    @SerializedName("max_players")
    String maxPlayers;
    @SerializedName("gamemode")
    String gamemode;
    @SerializedName("last_restart")
    int lastRestart;

    public String getSampAddress() {
        return sampAddress;
    }

    public String getSampPort() {
        return sampPort;
    }

    public String getServerStatus() {
        return serverStatus;
    }

    public String getNumPlayers() {
        return numPlayers;
    }

    public String getMaxPlayers() {
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
}
