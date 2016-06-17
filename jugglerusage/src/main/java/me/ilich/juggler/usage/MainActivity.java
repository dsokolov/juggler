package me.ilich.juggler.usage;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.fragments.StubNavigationFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;

public class MainActivity extends JugglerActivity {

    @Override
    protected State createState() {
        return StateFactory.contentToolbarNavigationState(null, StubNavigationFragment.class, null);
    }

}
