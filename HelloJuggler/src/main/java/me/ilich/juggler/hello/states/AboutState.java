package me.ilich.juggler.hello.states;

import android.support.v7.app.ActionBar;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.AboutFragment;
import me.ilich.juggler.hello.gui.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.State;

public class AboutState extends ContentToolbarNavigationState<State.Params> {

    public AboutState() {
        super(null);
    }

    @Override
    public int getTitleRes() {
        return R.string.title_about;
    }

    @Override
    protected JugglerFragment onCreateContent(Params params) {
        return AboutFragment.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(Params params) {
        return StandardToolbarFragment.create(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
    }

    @Override
    protected JugglerFragment onCreateNavigation(Params params) {
        return StandardNavigationFragment.create(1);
    }

}
