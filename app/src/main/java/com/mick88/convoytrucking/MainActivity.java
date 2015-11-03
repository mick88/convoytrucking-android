package com.mick88.convoytrucking;

import android.os.Bundle;

import com.mick88.convoytrucking.base.BaseActivity;
import com.mick88.convoytrucking.chat.ChatFragment;

/**
 * Created by Michal on 03/11/2015.
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ChatFragment fragment = new ChatFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        }
    }
}
