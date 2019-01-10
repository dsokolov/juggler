package me.ilich.juggler.hello.states;


import androidx.annotation.Nullable;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.fragments.OnlyContentFragment;
import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.VoidParams;

public class RegistrationDoneState extends ContentOnlyState<VoidParams> {

    public RegistrationDoneState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
        return OnlyContentFragment.create();
    }

}
