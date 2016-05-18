package me.ilich.juggler.hello.states.toolbarpos;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.fragments.toolbarpos.ToolbarposAFragment;
import me.ilich.juggler.states.ContentDoubleToolbarState;
import me.ilich.juggler.states.VoidParams;

public class ToolbarStateA extends ContentDoubleToolbarState<VoidParams> {

    public ToolbarStateA() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContentBelow(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Override
    protected JugglerFragment onConvertContentUnder(VoidParams params, @Nullable JugglerFragment fragment) {
        return ToolbarposAFragment.create();
    }

    @Override
    protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Override
    public String getTitle(Context context, VoidParams params) {
        return "A";
    }

    @Override
    public Drawable getUpNavigationIcon(Context context, VoidParams params) {
        return context.getResources().getDrawable(R.drawable.ic_menu_black_24dp);
    }

}
