package com.mick88.convoytrucking.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.mick88.convoytrucking.R;
import com.mick88.convoytrucking.base.BaseActivity;

/**
 * Created by Michal on 22/06/2016.
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initToolbar();

        final ViewGroup buttonLayout = (ViewGroup) findViewById(R.id.buttonLayout);
        for (int i = 0; i < buttonLayout.getChildCount(); i++) {
            buttonLayout.getChildAt(i).setOnClickListener(this);
        }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnWebsite:
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://convoytrucking.net/"));
                startActivity(websiteIntent);
                break;
            case R.id.btnForum:
                Intent forumIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://forum.convoytrucking.net/"));
                startActivity(forumIntent);
                break;

            case R.id.btnGithub:
                Intent githubIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mick88/convoytrucking2"));
                startActivity(githubIntent);
                break;

            case R.id.btnShare:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Convoy Trucking app");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.mick88.convoytrucking");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
        }
    }
}
