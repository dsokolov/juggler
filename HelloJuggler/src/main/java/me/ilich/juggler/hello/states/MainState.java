package me.ilich.juggler.hello.states;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.VoidParams;

public class MainState extends ContentToolbarNavigationState<VoidParams> {

    public static final String TAG = "main";

    public MainState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return MainFragment.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.create();
    }

    @Override
    protected JugglerFragment onCreateNavigation(VoidParams params) {
        return StandardNavigationFragment.create(0);
    }

    @Override
    public String getTag() {
        return TAG;
    }

}
