package me.ilich.juggler.usage.navigate.newstate;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.usage.R;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.navigate.Tools;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class NewActivityContentOnlyTestCase extends ActivityInstrumentationTestCase2<JugglerActivity> {

    public NewActivityContentOnlyTestCase() {
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
        JugglerActivity.addState(intent, StateFactory.contentOnlyState(null));
        setActivityIntent(intent);
        getActivity();
        onView(ViewMatchers.withText(R.string.stub_text_content)).check(doesNotExist());
        onView(withText(R.string.stub_text_content)).check(doesNotExist());
        onView(withText(R.string.stub_text_title)).check(doesNotExist());
        onView(withText(R.string.stub_text_navigation)).check(doesNotExist());
    }

    public void testContentOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentOnlyState(StubContentFragment.class));
        setActivityIntent(intent);
        getActivity();
        Tools.check(true, false, false);
    }

}
