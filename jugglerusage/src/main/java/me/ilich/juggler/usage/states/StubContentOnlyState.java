package me.ilich.juggler.usage.states;

import android.content.Context;
import android.support.annotation.Nullable;

import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.VoidParams;
import me.ilich.juggler.usage.fragments.StubContentFragment;
import me.ilich.juggler.gui.JugglerFragment;

public class StubContentOnlyState extends ContentOnlyState<VoidParams> {

    public StubContentOnlyState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
        return StubContentFragment.create();
    }

    @Nullable
    @Override
    public String getTitle(Context context, VoidParams params) {
        return "Stub";
    }
}
