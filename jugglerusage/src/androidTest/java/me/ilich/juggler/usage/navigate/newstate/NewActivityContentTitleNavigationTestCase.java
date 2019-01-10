package me.ilich.juggler.usage.navigate.newstate;

import android.content.Intent;

import androidx.test.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.fragments.StubNavigationFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;
import me.ilich.juggler.usage.navigate.Tools;

public class NewActivityContentTitleNavigationTestCase extends ActivityTestRule<JugglerActivity> {

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
        State state = StateFactory.contentToolbarNavigationState(null, null, null);
        setActivityIntent(JugglerActivity.addState(null, state));
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkNull();
            }
        });
    }

    public void testContentOnly() {
        State state = StateFactory.contentToolbarNavigationState(null, null, StubContentFragment.class);
        setActivityIntent(JugglerActivity.addState(null, state));
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
        State state = StateFactory.contentToolbarNavigationState(title, icon, StubToolbarFragment.class, null, null);
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

    public void testNavigationOnly() {
        State state = StateFactory.contentToolbarNavigationState(null, StubNavigationFragment.class, null);
        setActivityIntent(JugglerActivity.addState(null, state));
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkNavigationOnly();
            }
        });
    }

    public void testContentToolbar() {
        final String title = "12345";
        final int icon = android.R.drawable.ic_menu_help;
        State state = StateFactory.contentToolbarNavigationState(title, icon, StubToolbarFragment.class, null, StubContentFragment.class);
        setActivityIntent(JugglerActivity.addState(null, state));
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkContentToolbar();
                Tools.check(title, icon);
            }
        });
    }

    public void testContentNavigation() {
        State state = StateFactory.contentToolbarNavigationState(null, StubNavigationFragment.class, StubContentFragment.class);
        setActivityIntent(JugglerActivity.addState(null, state));
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkContentNavigation();
            }
        });
    }

    public void testNavigationToolbar() {
        final String title = "12345";
        final int icon = android.R.drawable.ic_menu_help;
        State state = StateFactory.contentToolbarNavigationState(title, icon, StubToolbarFragment.class, StubNavigationFragment.class, null);
        Intent intent = new Intent();
        JugglerActivity.addState(intent, state);
        setActivityIntent(intent);
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkToolbarNavigation();
                Tools.check(title, icon);
            }
        });
    }

    public void testContentToolbarNavigation() {
        final String title = "12345";
        final int icon = android.R.drawable.ic_menu_help;
        State state = StateFactory.contentToolbarNavigationState(title, icon, StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        Intent intent = new Intent();
        JugglerActivity.addState(intent, state);
        setActivityIntent(intent);
        getActivity();
        Tools.checkOrientations(this, new Runnable() {

            @Override
            public void run() {
                Tools.checkContentToolbarNavigation();
                Tools.check(title, icon);
            }
        });
    }

}
