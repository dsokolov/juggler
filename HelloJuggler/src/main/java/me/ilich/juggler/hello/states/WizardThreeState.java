package me.ilich.juggler.hello.states;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.fragments.WizardThreeFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.VoidParams;

public class WizardThreeState extends ContentBelowToolbarState<VoidParams> {

    public WizardThreeState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return new WizardThreeFragment();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.createTitleBack();
    }

}
