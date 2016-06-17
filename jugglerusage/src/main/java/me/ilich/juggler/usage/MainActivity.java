package me.ilich.juggler.usage;

import android.graphics.drawable.Drawable;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.usage.fragments.StubToolbarFragment;

public class MainActivity extends JugglerActivity {

    @Override
    protected State createState() {
        String title = "12345";
        int icon = android.R.drawable.ic_menu_help;
        //State state = StateFactory.contentToolbarNavigationState(title, icon, StubToolbarFragment.class, StubNavigationFragment.class, StubContentFragment.class);
        State state = StateFactory.contentBelowToolbarState(title, icon, StubToolbarFragment.class, StubContentFragment.class);
        return state;
    }

}
