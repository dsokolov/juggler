package me.ilich.juggler.hello.states;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.fragments.FragmentA;
import me.ilich.juggler.hello.gui.fragments.StandardNavigationFragmentA;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragmentA;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.NavigationContentDoubleToolbarState;
import me.ilich.juggler.states.VoidParams;

public class StateA extends NavigationContentDoubleToolbarState<VoidParams> {

    public static final String TAG = "State_A";

    public StateA() {
        super(VoidParams.instance());
    }

    @Override
    public String getTitle(Context context, VoidParams params) {
        return context.getString(R.string.title_state_a);
    }

    @Override
    public Drawable getUpNavigationIcon(Context context, VoidParams params) {
        return context.getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
    }

    @Override
    protected JugglerFragment onConvertContentBelow(VoidParams params, @Nullable JugglerFragment fragment) {
        return FragmentA.newInstance();
    }

    @Override
    protected JugglerFragment onConvertContentUnder(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Override
    protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
        //return StandardToolbarFragmentA.createTitleBack();
        return null;
    }

    @Override
    protected JugglerFragment onConvertNavigation(VoidParams params, @Nullable JugglerFragment fragment) {
        return StandardNavigationFragmentA.create(R.id.menu_state_a, false);
    }

    @Override
    public String getTag() {
        return TAG;
    }

}
