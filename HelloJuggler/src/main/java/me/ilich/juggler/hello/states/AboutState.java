package me.ilich.juggler.hello.states;

import android.content.Context;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.AboutFragment;
import me.ilich.juggler.hello.gui.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.State;

public class AboutState extends ContentToolbarNavigationState<State.Params> {

    public AboutState() {
        super(null);
    }

    @Override
    public int getTitleRes(Context context, Params params) {
        return R.string.title_about;
    }

    @Override
    protected JugglerFragment onCreateContent(Params params) {
        return AboutFragment.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(Params params) {
        return StandardToolbarFragment.createTitleBack();
    }

    @Override
    protected JugglerFragment onCreateNavigation(Params params) {
        return StandardNavigationFragment.create(1);
    }

}
