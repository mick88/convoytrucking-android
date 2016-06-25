package com.mick88.convoytrucking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Michal on 25/06/2016.
 */
public class LaunchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
