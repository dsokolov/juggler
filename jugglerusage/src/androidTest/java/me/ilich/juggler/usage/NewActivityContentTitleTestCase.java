package me.ilich.juggler.usage;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.VoidParams;
import me.ilich.juggler.usage.fragments.StubContentFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class NewActivityContentTitleTestCase extends ActivityInstrumentationTestCase2<JugglerActivity> {

    public NewActivityContentTitleTestCase() {
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
        JugglerActivity.addState(intent, new NullState());
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(ViewAssertions.doesNotExist());
        onView(withText(R.string.stub_text_title)).check(ViewAssertions.doesNotExist());
        onView(withText(R.string.stub_text_navigation)).check(ViewAssertions.doesNotExist());
    }

    public void testContentOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, new ContentState());
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withText(R.string.stub_text_title)).check(ViewAssertions.doesNotExist());
        onView(withText(R.string.stub_text_navigation)).check(ViewAssertions.doesNotExist());
    }

    public void testToolbarOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, new ToolbarState());
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(ViewAssertions.doesNotExist());
        onView(withText(R.string.stub_text_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withText(R.string.stub_text_navigation)).check(ViewAssertions.doesNotExist());
    }

    public void testContentToolbar() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, new ToolbarState());
        setActivityIntent(intent);
        getActivity();
        onView(withText(R.string.stub_text_content)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withText(R.string.stub_text_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withText(R.string.stub_text_navigation)).check(ViewAssertions.doesNotExist());
    }

    private static abstract class State extends ContentBelowToolbarState<VoidParams> {

        public State() {
            super(VoidParams.instance());
        }
    }

    private static class NullState extends State {

        @Override
        protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
            return null;
        }

        @Override
        protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
            return null;
        }

    }

    private static class ContentState extends State {

        @Override
        protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
            return StubContentFragment.create();
        }

        @Override
        protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
            return null;
        }

    }

    private static class ToolbarState extends State {

        @Override
        protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
            return null;
        }

        @Override
        protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
            return null; //TODO
        }

    }

    private static class ContentToolbarState extends State {

        @Override
        protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
            return StubContentFragment.create();
        }

        @Override
        protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
            return null; //TODO
        }

    }

}
