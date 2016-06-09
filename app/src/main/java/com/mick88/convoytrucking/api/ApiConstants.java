package com.mick88.convoytrucking.api;

import com.mick88.convoytrucking.BuildConfig;

/**
 * Created by Michal on 03/11/2015.
 */
public class ApiConstants {
    public static final String BASE_API = BuildConfig.BASE_URL + "api/";

    public static final String URL_STATICFILES = BuildConfig.BASE_URL + "static/";
    public static final String URL_MEDIA = BuildConfig.BASE_URL + "media/";

    public static final String API_NEWS = BASE_API + "news/";
    public static final String API_CHAT = BASE_API + "chat/";
    public static final String API_HOUSES = BASE_API + "houses/";
    public static final String API_PLAYERS = BASE_API + "players/";
    public static final String API_DEALERS = BASE_API + "dealers/";
    public static final String API_VEHICLES = BASE_API + "vehicles/";
    public static final String API_SERVER_INFO = BASE_API + "server-info";

    public static final String API_HOUSES_FORSALE = API_HOUSES + "?ownerid__isnull=true";

    public static final String ENCODING = "utf-8";
}
