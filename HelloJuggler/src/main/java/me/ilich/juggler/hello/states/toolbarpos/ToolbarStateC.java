package me.ilich.juggler.hello.states.toolbarpos;

import android.content.Context;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.fragments.toolbarpos.ToolbarposBFragment;
import me.ilich.juggler.hello.gui.fragments.toolbarpos.ToolbarposCFragment;
import me.ilich.juggler.states.ContentDoubleToolbarState;
import me.ilich.juggler.states.VoidParams;

public class ToolbarStateC extends ContentDoubleToolbarState<VoidParams> {

    public ToolbarStateC() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContentBelow(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Override
    protected JugglerFragment onConvertContentUnder(VoidParams params, @Nullable JugglerFragment fragment) {
        return new ToolbarposCFragment();
    }

    @Override
    protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
        return StandardToolbarFragment.create();
    }

    @Override
    public String getTitle(Context context, VoidParams params) {
        return "C";
    }

}
