package me.ilich.juggler.hello.states;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.fragments.MainFragment;
import me.ilich.juggler.hello.gui.fragments.StandardNavigationFragment;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragment;
import me.ilich.juggler.states.ContentNavigationState;
import me.ilich.juggler.states.ContentToolbarNavigationState;
import me.ilich.juggler.states.NavigationContentDoubleToolbarState;
import me.ilich.juggler.states.VoidParams;

public class MainState extends NavigationContentDoubleToolbarState<VoidParams> {

    public static final String TAG = "main";

    public MainState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContentUnder(VoidParams params, @Nullable JugglerFragment fragment) {
        return MainFragment.newInstance();
    }

    @Override
    protected JugglerFragment onConvertContentBelow(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Override
    protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Override
    protected JugglerFragment onConvertNavigation(VoidParams params, @Nullable JugglerFragment fragment) {
        return StandardNavigationFragment.create(0);
    }

    @Override
    public Drawable getUpNavigationIcon(Context context, VoidParams params) {
        return context.getResources().getDrawable(R.drawable.ic_menu_black_24dp);
    }

    @Nullable
    @Override
    public String getTitle(Context context, VoidParams params) {
        return context.getString(R.string.title_main);
    }

    @Override
    public String getTag() {
        return TAG;
    }

}
