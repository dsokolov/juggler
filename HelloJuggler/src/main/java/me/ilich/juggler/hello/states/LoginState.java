package me.ilich.juggler.hello.states;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.LoginFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.VoidParams;

public class LoginState extends ContentBelowToolbarState<VoidParams> {

    public LoginState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return LoginFragment.create();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.create();
    }

}
