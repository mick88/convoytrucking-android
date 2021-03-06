package com.mick88.convoytrucking.server_info;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.feeds.PlayerFeed;
import com.mick88.convoytrucking.api.schema.models.Player;
import com.mick88.convoytrucking.api.schema.models.ServerInfo;
import com.mick88.convoytrucking.api.schema.models.SimplePlayer;
import com.mick88.convoytrucking.base.ApiFragment;
import com.mick88.convoytrucking.players.PlayerActivity;

import java.util.Locale;

/**
 * Created by Michal on 21/11/2015.
 */
public class ServerInfoFragment extends ApiFragment<ServerInfo> implements AdapterView.OnItemClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_server_info, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUrl(ApiConstants.API_SERVER_INFO);
    }

    @Override
    protected Class<ServerInfo> getModelClass() {
        return ServerInfo.class;
    }

    @Override
    public void onResponse(ServerInfo info) {
        super.onResponse(info);
        setServerInfo(info);
    }

    @Override
    protected void sendRequest() {
        super.sendRequest();
        fetchStaff();
    }

    protected void fetchStaff() {
        final String url = ApiConstants.API_PLAYERS + "?mod__gte=" + Player.MOD_JRMOD;
        ModelRequest<PlayerFeed> request = new ModelRequest<>(url, PlayerFeed.class, this, new Response.Listener<PlayerFeed>() {
            @Override
            public void onResponse(PlayerFeed response) {
                final ArrayAdapter<Player> playerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, response.getResults());
                final ListView listView = (ListView) getView().findViewById(R.id.listView);
                listView.setAdapter(playerAdapter);
            }
        });
        sendRequest(request);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ListView staffListView = (ListView) view.findViewById(R.id.listView);
        staffListView.setOnItemClickListener(this);
    }

    protected void setServerInfo(ServerInfo serverInfo) {
        final View view = getView();
        assert view != null;
        final TextView tvServerAddress = (TextView) view.findViewById(R.id.tvServerAddress);
        final TextView tvGamemode = (TextView) view.findViewById(R.id.tvGamemode);
        final TextView tvPlayersOnline = (TextView) view.findViewById(R.id.tvPlayersOnline);
        final TextView tvUptime = (TextView) view.findViewById(R.id.tvUptime);
        final ProgressBar numPlayers = ((ProgressBar) view.findViewById(R.id.progressNumPlayers));

        tvServerAddress.setText(String.format(Locale.ENGLISH, "%s:%s", serverInfo.getSampAddress(), serverInfo.getSampPort()));
        tvGamemode.setText(serverInfo.getGamemode());
        tvPlayersOnline.setText(getString(R.string.players_online_s, serverInfo.getNumPlayers(), serverInfo.getMaxPlayers()));
        final int h = serverInfo.getUptime() / 60 / 60;
        final String uptime;
        if (h < 24) {
            uptime = String.format(Locale.ENGLISH, "%d hours", h);
        } else {
            uptime = String.format(Locale.ENGLISH, "%d days", h / 24);
        }
        tvUptime.setText(getString(R.string.uptime_s, uptime));

        if (serverInfo.getMaxPlayers() != null) {
            numPlayers.setVisibility(View.VISIBLE);
            numPlayers.setMax(serverInfo.getMaxPlayers());
            numPlayers.setProgress(serverInfo.getNumPlayers());
        } else {
            numPlayers.setVisibility(View.GONE);
        }

        int icon = 0;
        switch (serverInfo.getServerStatus()) {
            case ServerInfo.SERVER_STATUS_ONLINE:
                icon = R.drawable.ic_status_online;
                break;
            case ServerInfo.SERVER_STATUS_OFFLINE:
                icon = R.drawable.ic_status_offline;
                break;
            case ServerInfo.SERVER_STATUS_LOCKED:
                icon = R.drawable.ic_status_locked;
                break;
        }
        tvServerAddress.setCompoundDrawablesWithIntrinsicBounds(0, 0, icon, 0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Object item = parent.getItemAtPosition(position);
        if (item instanceof SimplePlayer) {
            SimplePlayer player = ((SimplePlayer) item);
            final Intent intent = PlayerActivity.createIntent(getContext(), player);
            startActivity(intent);
        }
    }
}
