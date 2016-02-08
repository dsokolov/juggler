package me.ilich.juggler.hello.states;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardTwoFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.State;

public class WizardTwoState extends ContentBelowToolbarState<State.Params> {

    public WizardTwoState() {
        super(null);
    }

    @Override
    public int getTitleRes() {
        return R.string.title_wizard_two;
    }

    @Override
    protected JugglerFragment onCreateContent(Params params) {
        return new WizardTwoFragment();
    }

    @Override
    protected JugglerFragment onCreateToolbar(Params params) {
        StandardToolbarFragment standardToolbarFragment = StandardToolbarFragment.create(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
        return standardToolbarFragment;
    }

}
