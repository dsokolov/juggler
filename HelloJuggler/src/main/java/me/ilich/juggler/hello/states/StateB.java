package me.ilich.juggler.hello.states;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.fragments.FragmentB;
import me.ilich.juggler.hello.gui.fragments.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.fragments.StandardNavigationFragmentB;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragmentB;
import me.ilich.juggler.states.NavigationContentDoubleToolbarState;
import me.ilich.juggler.states.VoidParams;

public class StateB extends NavigationContentDoubleToolbarState<VoidParams> {

    public StateB() {
        super(VoidParams.instance());
    }

    @Override
    public String getTitle(Context context, VoidParams params) {
        return context.getString(R.string.title_state_b);
    }

    @Override
    protected JugglerFragment onConvertContentBelow(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Override
    protected JugglerFragment onConvertContentUnder(VoidParams params, @Nullable JugglerFragment fragment) {
        return FragmentB.newInstance();
    }

    @Override
    protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
        return StandardToolbarFragmentB.createTitleBack();
    }

    @Override
    protected JugglerFragment onConvertNavigation(VoidParams params, @Nullable JugglerFragment fragment) {
        return StandardNavigationFragment.create(R.id.menu_state_b);
    }

    @Override
    protected Drawable getUpNavigationIcon(Context context, VoidParams params) {
        return context.getResources().getDrawable(android.R.drawable.ic_menu_gallery);
    }

}
