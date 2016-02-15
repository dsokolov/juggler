package me.ilich.juggler.hello.states;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.State;

public class MainState extends ContentToolbarNavigationState<State.Params> {

    public static final String TAG = "main";

    public MainState() {
        super(null);
    }

    @Override
    protected JugglerFragment onCreateContent(Params params) {
        return MainFragment.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(Params params) {
        return StandardToolbarFragment.create(ActionBar.DISPLAY_HOME_AS_UP);
    }

    @Override
    protected JugglerFragment onCreateNavigation(Params params) {
        return StandardNavigationFragment.create(0);
    }

    @Override
    public String getTag() {
        return TAG;
    }

}
