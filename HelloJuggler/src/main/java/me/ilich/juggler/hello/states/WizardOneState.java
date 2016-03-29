package me.ilich.juggler.hello.states;

import android.content.Context;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardOneFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.VoidParams;

public class WizardOneState extends ContentBelowToolbarState<VoidParams> {

    public WizardOneState() {
        super(VoidParams.instance());
    }

    @Override
    public String getTitle(Context context, VoidParams params) {
        return "Wizard One";
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return new WizardOneFragment();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.createTitleBack();
    }

}
