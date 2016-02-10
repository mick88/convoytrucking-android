package com.mick88.convoytrucking;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.mick88.convoytrucking.api.VolleySingleton;
import com.mick88.convoytrucking.players.PlayerActivity;
import com.mick88.convoytrucking.server_info.ServerInfoFragment;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.assertNoUnverifiedIntents;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.object.HasToString.hasToString;

/**
 * Created by Michal on 06/02/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ServerInfoInstrumentationTest {
    MainActivity activity;

    @Rule
    public IntentsTestRule<MainActivity> activityTestRule = new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        activity = activityTestRule.getActivity();
        Espresso.registerIdlingResources(new VolleyIdlingResource(VolleySingleton.getInstance(activity).getRequestQueue()));
        activity.showFragment(new ServerInfoFragment());
    }

    @Test
    public void testServerInfo() throws Exception {
        onView(ViewMatchers.withId(R.id.tvServerAddress))
                .check(matches(withText("server.convoytrucking.net:7777")))
                .check(matches(isDisplayed()));

        onView(ViewMatchers.withId(R.id.tvGamemode))
                .check(matches(withText("Convoy Trucking 2.13")));

        onView(ViewMatchers.withId(R.id.tvPlayersOnline))
                .check(matches(withText("Players online: 7/100")));
    }

    @Test
    public void testStaffList() throws Exception {
        final Matcher<View> listViewMatcher = withId(R.id.listView);
        onView(listViewMatcher).check(matches(isDisplayed()));
        onData(hasToString("mick88"))
                .inAdapterView(listViewMatcher).perform(click());

        intended(toPackage(BuildConfig.APPLICATION_ID));
        intended(hasExtras(allOf(
                hasEntry(PlayerActivity.EXTRA_PLAYER_NAME, "mick88"),
                hasEntry(PlayerActivity.EXTRA_PLAYER_ID, 1L)
        )));

        assertNoUnverifiedIntents();
    }
}
