package me.ilich.juggler.usage.navigate;

import android.test.ActivityInstrumentationTestCase2;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.NoActivityResumedException;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.StubContentFragment;

import static androidx.test.espresso.Espresso.pressBack;

public class BackTestCase extends ActivityInstrumentationTestCase2<JugglerActivity> {

    public BackTestCase() {
        super(JugglerActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        setActivityInitialTouchMode(true);
    }

    public void testBackOneTime() {
        setActivityIntent(JugglerActivity.addState(null, StateFactory.contentOnlyState(StubContentFragment.class)));
        getActivity();
        try {
            pressBack();
            fail();
        } catch (NoActivityResumedException e) {
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testBackTwoTime() {
        setActivityIntent(JugglerActivity.addState(null, StateFactory.contentOnlyState(StubContentFragment.class)));
        getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getActivity().navigateTo().state(Add.deeper(StateFactory.contentOnlyState(StubContentFragment.class)));
            }
        });
        pressBack();
        try {
            pressBack();
            fail();
        } catch (NoActivityResumedException e) {
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
