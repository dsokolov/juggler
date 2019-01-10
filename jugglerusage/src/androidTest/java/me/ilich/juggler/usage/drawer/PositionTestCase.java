package me.ilich.juggler.usage.drawer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.core.view.GravityCompat;
import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.R;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.fragments.StubNavigationFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isOpen;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
