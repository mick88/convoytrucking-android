package com.mick88.convoytrucking.players;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.api.ApiConstants;
import com.mick88.convoytrucking.api.ModelRequest;
import com.mick88.convoytrucking.api.schema.models.Player;
import com.mick88.convoytrucking.api.schema.models.SimplePlayer;
import com.mick88.convoytrucking.base.BaseActivity;
import com.mick88.convoytrucking.utils.FormatUtils;

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
        setData(response);
    }

    protected void setData(Player player) {
        final TextView
                tvPlayer = ((TextView) findViewById(R.id.tvPlayer)),
                tvConvoyScore = ((TextView) findViewById(R.id.tvConvoyScore)),
                tvTruckLoads = ((TextView) findViewById(R.id.tvTruckLoads)),
                tvLastSeen = ((TextView) findViewById(R.id.tvLastSeen)),
                tvScore = ((TextView) findViewById(R.id.tvScore));

        final String name = player.getName();
        tvPlayer.setText(name);
        int staffBadge = 0;
        int vipBadge = 0;
        if (player.isVip()) {
            vipBadge = R.drawable.ic_vip;
        }
        if (player.isStaff()) {
            staffBadge = R.drawable.ic_staff;
            final TextView tvStaff = (TextView) findViewById(R.id.tvPlayerStaff);
            tvStaff.setVisibility(View.VISIBLE);
            switch (player.getMod()) {
                case Player.MOD_ADMINISTRATOR:
                    tvStaff.setText(R.string.staff_admin);
                    break;
                case Player.MOD_MODERATOR:
                    tvStaff.setText(R.string.staff_mod);
                    break;
                case Player.MOD_JRMOD:
                    tvStaff.setText(R.string.staff_jrmod);
                    break;
            }
        }
        tvPlayer.setCompoundDrawablesWithIntrinsicBounds(vipBadge, 0, staffBadge, 0);

        // scores
        final String score = FormatUtils.formatLargeNumber(player.getScore());
        final String TruckLoads = FormatUtils.formatLargeNumber(player.getStatistics().getTruckLoads());
        final String convoyScore = FormatUtils.formatLargeNumber(player.getConvoyScore());
        tvScore.setText(getString(R.string.score_s, score));
        tvConvoyScore.setText(getString(R.string.convoy_score_s, convoyScore));
        tvTruckLoads.setText(getString(R.string.truck_loads_s, TruckLoads));

        // last seen
        final int lastSeen = player.getLastSeen();
        final String timeAgo = FormatUtils.formatTimeAgo(lastSeen);
        tvLastSeen.setText(getString(R.string.last_seen_s, timeAgo));
    }
}
