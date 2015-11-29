package com.mick88.convoytrucking.players;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.volley.Response;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.models.Player;
import com.mick88.convoytrucking.api.schema.models.SimplePlayer;
import com.mick88.convoytrucking.base.BaseActivity;

/**
 * Created by Michal on 27/11/2015.
 */
public class PlayerActivity extends BaseActivity implements Response.Listener<Player> {
    public static String
            EXTRA_PLAYER_NAME = "player_name",
            EXTRA_PLAYER_ID = "player_id";

    public static Intent createIntent(Context context, SimplePlayer player) {
        final Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(EXTRA_PLAYER_ID, (long) player.getId());
        intent.putExtra(EXTRA_PLAYER_NAME, player.getName());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initToolbar();
        downloadData();

        setTitle(getIntent().getStringExtra(EXTRA_PLAYER_NAME));
    }

    @Override
    protected Toolbar initToolbar() {
        final Toolbar toolbar = super.initToolbar();
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        return toolbar;
    }

    protected void downloadData() {
        final long playerId = getIntent().getLongExtra(EXTRA_PLAYER_ID, 0);
        final String url = ApiConstants.API_PLAYERS + playerId;
        ModelRequest<Player> request = new ModelRequest<>(url, Player.class, this, this);
        sendRequest(request);
    }

    @Override
    public void onResponse(Player response) {
        final TextView tvPlayer = ((TextView) findViewById(R.id.tvPlayer));
        tvPlayer.setText(response.getName());
    }
}
