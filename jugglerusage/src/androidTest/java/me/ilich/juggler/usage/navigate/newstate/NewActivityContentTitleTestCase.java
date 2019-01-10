package me.ilich.juggler.usage.navigate.newstate;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import androidx.test.InstrumentationRegistry;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;
import me.ilich.juggler.usage.navigate.Tools;

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
        JugglerActivity.addState(intent, StateFactory.contentBelowToolbarState(null, null));
        setActivityIntent(intent);
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkNull();
            }
        });

    }

    public void testContentOnly() {
        Intent intent = new Intent();
        JugglerActivity.addState(intent, StateFactory.contentBelowToolbarState(null, StubContentFragment.class));
        setActivityIntent(intent);
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkContentOnly();
            }
        });
    }

    public void testToolbarOnly() {
        final String title = "12345";
        final int icon = android.R.drawable.ic_menu_help;
        State state = StateFactory.contentBelowToolbarState(title, icon, StubToolbarFragment.class, null);
        setActivityIntent(JugglerActivity.addState(null, state));
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkToolbarOnly();
                Tools.check(title, icon);
            }
        });
    }

    public void testContentToolbar() {
        final String title = "12345";
        final int icon = android.R.drawable.ic_menu_help;
        State state = StateFactory.contentBelowToolbarState(title, icon, StubToolbarFragment.class, StubContentFragment.class);
        Intent intent = new Intent();
        JugglerActivity.addState(intent, state);
        setActivityIntent(intent);
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkContentToolbar();
                Tools.check(title, icon);
            }
        });
    }

}
