package me.ilich.juggler.hello.states;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardThreeFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.State;

public class WizardThreeState extends ContentBelowToolbarState<State.Params> {

    public WizardThreeState() {
        super(null);
    }

    @Override
    protected JugglerFragment onCreateContent(Params params) {
        return new WizardThreeFragment();
    }

    @Override
    protected JugglerFragment onCreateToolbar(Params params) {
        StandardToolbarFragment standardToolbarFragment = StandardToolbarFragment.create(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        //standardToolbarFragment.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        return standardToolbarFragment;
    }

}
