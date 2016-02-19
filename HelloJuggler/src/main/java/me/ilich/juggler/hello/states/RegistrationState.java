package me.ilich.juggler.hello.states;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.RegistrationFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.VoidParams;

public class RegistrationState extends ContentBelowToolbarState<VoidParams> {

    public RegistrationState() {
        super(null);
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return RegistrationFragment.create();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.createBack();
    }

}
