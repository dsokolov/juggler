package me.ilich.juggler.usage.navigate.deeper;


import androidx.test.espresso.Espresso;
import me.ilich.juggler.change.Add;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.StateFactory;
import me.ilich.juggler.usage.fragments.Stub2ContentFragment;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.navigate.Tools;
import me.ilich.juggler.usage.navigate.Tools2;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class DeeperTestCase extends ActivityInstrumentationTestCase2<JugglerActivity> {

    public DeeperTestCase() {
        super(JugglerActivity.class);
    }

    public void testA() {
        State state1 = StateFactory.contentOnlyState(StubContentFragment.class);
        final State state2 = StateFactory.contentOnlyState(Stub2ContentFragment.class);
        setActivityIntent(JugglerActivity.addState(null, state1));
        getActivity();
        Tools.checkContentOnly();
        Tools2.checkNull();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getActivity().navigateTo().state(Add.deeper(state2));
            }
        });
        Tools.checkNull();
        Tools2.checkContentOnly();
        Espresso.pressBack();
        Tools.checkContentOnly();
        Tools2.checkNull();
    }

}
