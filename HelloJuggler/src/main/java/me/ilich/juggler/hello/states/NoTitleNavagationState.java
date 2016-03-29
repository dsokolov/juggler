package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.OnlyContentFragment;
import me.ilich.juggler.hello.gui.StandardNavigationFragment;
import me.ilich.juggler.states.ContentNavigationState;
import me.ilich.juggler.states.VoidParams;

public class NoTitleNavagationState extends ContentNavigationState<VoidParams> {

    public NoTitleNavagationState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return new OnlyContentFragment();
    }

    @Override
    protected JugglerFragment onCreateNavigation(VoidParams params) {
        return StandardNavigationFragment.create(2);
    }

}
