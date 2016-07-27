package me.ilich.juggler.usage.drawer;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class OpenCloseTestCases {

    @Rule
    public final ActivityTestRule<JugglerActivity> activityTestRule = new ActivityTestRule<>(JugglerActivity.class, true);

    @Test
    public void openIfExists() {
        final State state1 = StateFactory.contentToolbarNavigationState(StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().navigateTo().state(Add.deeper(state1));
            }
        });
        try {
            activityTestRule.getActivity().getJuggler().openDrawer();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        onView(withText(R.string.stub_text_navigation)).check(matches(isDisplayed()));
    }

    @Test
    public void closeIfExists() {
        final State state1 = StateFactory.contentToolbarNavigationState(StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().navigateTo().state(Add.deeper(state1));
            }
        });
        activityTestRule.getActivity().getJuggler().openDrawer();
        try {
            activityTestRule.getActivity().getJuggler().closeDrawer();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        onView(withText(R.string.stub_text_navigation)).check(matches(not(isDisplayed())));
    }

    @Test
    public void openIfNotExists() {
        final State state1 = StateFactory.contentBelowToolbarState(StubToolbarFragment.class, StubContentFragment.class);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().navigateTo().state(Add.deeper(state1));
            }
        });
        try {
            activityTestRule.getActivity().getJuggler().openDrawer();
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }

    @Test
    public void closeIfNotExists() {
        final State state1 = StateFactory.contentBelowToolbarState(StubToolbarFragment.class, StubContentFragment.class);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activityTestRule.getActivity().navigateTo().state(Add.deeper(state1));
            }
        });
        try {
            activityTestRule.getActivity().getJuggler().closeDrawer();
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }

}
