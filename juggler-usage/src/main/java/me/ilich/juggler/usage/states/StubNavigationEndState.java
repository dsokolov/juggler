package me.ilich.juggler.usage.states;

import android.content.Context;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.ContentToolbarNavigationEndState;
import me.ilich.juggler.states.VoidParams;
import me.ilich.juggler.usage.fragments.StubContentFragment;

public class StubNavigationEndState extends ContentToolbarNavigationEndState<VoidParams> {

    public StubNavigationEndState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
        return StubContentFragment.create();
    }

    @Override
    protected JugglerFragment onConvertToolbar(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Override
    protected JugglerFragment onConvertNavigation(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Nullable
    @Override
    public String getTitle(Context context, VoidParams params) {
        return "Stub";
    }
}
