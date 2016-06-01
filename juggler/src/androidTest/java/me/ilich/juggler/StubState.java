package me.ilich.juggler;

import android.content.Context;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.VoidParams;

public class StubState extends ContentOnlyState<VoidParams> {

    public StubState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
        return null;
    }

    @Nullable
    @Override
    public String getTitle(Context context, VoidParams params) {
        return "Stub";
    }
}
