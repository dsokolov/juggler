package me.ilich.juggler.hello.states;

import android.content.Context;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.fragments.FragmentA;
import me.ilich.juggler.hello.gui.fragments.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.fragments.StandardNavigationFragmentA;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragmentA;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.VoidParams;

public class StateA extends ContentToolbarNavigationState<VoidParams> {

    public static final String TAG = "State_A";

    public StateA() {
        super(VoidParams.instance());
    }

    @Override
    public String getTitle(Context context, VoidParams params) {
        return context.getString(R.string.title_state_a);
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return FragmentA.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragmentA.createTitleBack();
    }

    @Override
    protected JugglerFragment onCreateNavigation(VoidParams params) {
        return StandardNavigationFragmentA.create(R.id.menu_state_a);
    }

    @Override
    public String getTag() {
        return TAG;
    }

}
