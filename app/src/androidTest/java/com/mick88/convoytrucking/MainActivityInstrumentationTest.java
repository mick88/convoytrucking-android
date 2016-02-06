package com.mick88.convoytrucking;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Michal on 05/02/2016.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {
    MainActivity activity;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
    }

    @Test
    public void testActivityLaunch() throws Exception {
        Assert.assertNotNull(activity);
    }
}