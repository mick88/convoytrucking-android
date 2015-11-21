package com.mick88.convoytrucking.server_info;

import android.os.Bundle;
import android.widget.Toast;

import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.schema.models.ServerInfo;
import com.mick88.convoytrucking.base.ApiFragment;

/**
 * Created by Michal on 21/11/2015.
 */
public class ServerInfoFragment extends ApiFragment<ServerInfo> {
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
    public void onResponse(ServerInfo response) {
        super.onResponse(response);
        Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
    }
}
