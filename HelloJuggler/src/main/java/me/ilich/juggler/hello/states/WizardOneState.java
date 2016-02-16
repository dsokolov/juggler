package me.ilich.juggler.hello.states;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardOneFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.State;

public class WizardOneState extends ContentBelowToolbarState<State.Params> {

    public WizardOneState() {
        super(null);
    }

    @Override
    public String getTitle(Params params) {
        return "Wizard One";
    }

    @Override
    protected JugglerFragment onCreateContent(Params params) {
        return new WizardOneFragment();
    }

    @Override
    protected JugglerFragment onCreateToolbar(Params params) {
        return StandardToolbarFragment.createTitleBack();
    }

}
