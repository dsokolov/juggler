package me.ilich.juggler.hello.states;

import android.content.Context;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.fragments.FragmentC;
import me.ilich.juggler.hello.gui.fragments.StandardNavigationFragmentC;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragmentC;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.VoidParams;

public class StateC extends ContentToolbarNavigationState<VoidParams> {

    public StateC() {
        super(VoidParams.instance());
    }

    @Override
    public String getTitle(Context context, VoidParams params) {
        return context.getString(R.string.title_state_c);
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return FragmentC.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragmentC.createTitleBack();
    }

    @Override
    protected JugglerFragment onCreateNavigation(VoidParams params) {
        return StandardNavigationFragmentC.create(R.id.menu_state_c);
    }

}
