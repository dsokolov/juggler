package me.ilich.juggler.usage.navigate.newstate;

import androidx.test.InstrumentationRegistry;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.navigate.Tools;

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
        State state = StateFactory.contentOnlyState(null);
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
        State state = StateFactory.contentOnlyState(StubContentFragment.class);
        setActivityIntent(JugglerActivity.addState(null, state));
        getActivity();
        Tools.checkOrientations(this, new Runnable(){

            @Override
            public void run() {
                Tools.checkContentOnly();
            }
        });
    }

}
