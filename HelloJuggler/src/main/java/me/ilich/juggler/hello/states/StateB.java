package me.ilich.juggler.hello.states;

import android.content.Context;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.fragments.AFragment;
import me.ilich.juggler.hello.gui.fragments.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragment;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.VoidParams;

public class StateB extends ContentToolbarNavigationState<VoidParams> {

    public StateB() {
        super(VoidParams.instance());
    }

    @Override
    public String getTitle(Context context, VoidParams params) {
        return context.getString(R.string.title_state_b);
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return AFragment.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.createTitleBack();
    }

    @Override
    protected JugglerFragment onCreateNavigation(VoidParams params) {
        return StandardNavigationFragment.create(R.id.menu_state_b);
    }

}
