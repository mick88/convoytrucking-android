package com.mick88.convoytrucking.server_info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.schema.models.ServerInfo;
import com.mick88.convoytrucking.base.ApiFragment;

import java.util.Locale;

/**
 * Created by Michal on 21/11/2015.
 */
public class ServerInfoFragment extends ApiFragment<ServerInfo> {
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
        if (isAdded()) {
            final View view = getView();
            assert view != null;
            final TextView tvServerAddress = (TextView) view.findViewById(R.id.tvServerAddress);
            final TextView tvGamemode = (TextView) view.findViewById(R.id.tvGamemode);
            final TextView tvPlayersOnline = (TextView) view.findViewById(R.id.tvPlayersOnline);

            tvServerAddress.setText(String.format(Locale.ENGLISH, "%s:%s", info.getSampAddress(), info.getSampPort()));
            tvGamemode.setText(info.getGamemode());
            tvPlayersOnline.setText(getString(R.string.players_online_s, info.getNumPlayers(), info.getMaxPlayers()));
        }
    }
}
