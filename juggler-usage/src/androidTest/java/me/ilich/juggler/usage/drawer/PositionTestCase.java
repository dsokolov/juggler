package me.ilich.juggler.usage.drawer;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.view.GravityCompat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.R;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.fragments.StubNavigationFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isOpen;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class PositionTestCase {

    @Rule
    public final ActivityTestRule<JugglerActivity> activityTestRule = new ActivityTestRule<>(JugglerActivity.class, true, true);

    @Test
    public void gravityStart() {
        final State state1 = StateFactory.contentToolbarNavigationState(StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().navigateTo().state(Add.deeper(state1));
            }
        });
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open(GravityCompat.START));
        onView(withId(R.id.drawer_layout)).check(matches(isOpen(GravityCompat.START)));
    }

    @Test
    public void gravityEnd() {
        final State state1 = StateFactory.contentToolbarNavigationEndState(StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().navigateTo().state(Add.deeper(state1));
            }
        });
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open(GravityCompat.END));
        onView(withId(R.id.drawer_layout)).check(matches(isOpen(GravityCompat.END)));
    }

}
