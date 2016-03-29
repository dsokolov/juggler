package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.OnlyContentFragment;
import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.VoidParams;

public class RegistrationDoneState extends ContentOnlyState<VoidParams> {

    public RegistrationDoneState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return OnlyContentFragment.create();
    }

}
