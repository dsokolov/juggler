package me.ilich.juggler.usage.navigate.newstate;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.v7.widget.Toolbar;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import android.widget.TextView;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.R;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.fragments.StubNavigationFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;
import me.ilich.juggler.usage.navigate.Tools;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static me.ilich.juggler.usage.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.core.AllOf.allOf;

public class NewActivityContentTitleNavigationTestCase extends ActivityInstrumentationTestCase2<JugglerActivity> {

    public NewActivityContentTitleNavigationTestCase() {
        super(JugglerActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        setActivityInitialTouchMode(true);
    }

    public void testNull() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentToolbarNavigationState(null, null, null));
        setActivityIntent(intent);
        getActivity();
        onView(ViewMatchers.withText(R.string.stub_text_content)).check(doesNotExist());
        onView(withText(R.string.stub_text_title)).check(doesNotExist());
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

    public void testContentOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentToolbarNavigationState(null, null, StubContentFragment.class));
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_title)).check(doesNotExist());
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

    public void testToolbarOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentToolbarNavigationState(StubToolbarFragment.class, null, null));
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(doesNotExist());
        onView(withText(R.string.stub_text_title)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

    public void testNavigationOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentToolbarNavigationState(null, StubNavigationFragment.class, null));
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(doesNotExist());
        onView(withText(R.string.stub_text_title)).check(doesNotExist());
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText(R.string.stub_text_navigation)).check(matches(isDisplayed()));
    }

    public void testContentToolbar() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentToolbarNavigationState(StubToolbarFragment.class, null, StubContentFragment.class));
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_title)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

    public void testContentNavigation() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentToolbarNavigationState(null, StubNavigationFragment.class, StubContentFragment.class));
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(matches(isDisplayed()));
        onView(withText(R.string.stub_text_title)).check(doesNotExist());
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withText(R.string.stub_text_navigation)).check(matches(isDisplayed()));
    }

    public void testNavigationToolbar() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentToolbarNavigationState(StubToolbarFragment.class, StubNavigationFragment.class, null));
        setActivityIntent(intent);
        getActivity();

        Tools.check(false, true, true);
    }

    public void testContentToolbarNavigation() {
        String title = "12345";
        int icon = android.R.drawable.ic_menu_help;
        State state = StateFactory.contentToolbarNavigationState(title, icon, StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        Intent intent = new Intent();
        JugglerActivity.addState(intent, state);
        setActivityIntent(intent);
        getActivity();

        Tools.check(true, true, true);
        Tools.check(title, icon);
    }


}
