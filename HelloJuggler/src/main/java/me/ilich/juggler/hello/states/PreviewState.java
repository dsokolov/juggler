package me.ilich.juggler.hello.states;

import android.content.Context;

import androidx.annotation.Nullable;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.fragments.PreviewFragment;
import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.VoidParams;

public class PreviewState extends ContentOnlyState<VoidParams> {

    public PreviewState() {
        super(VoidParams.instance());
    }

    @Override
    protected JugglerFragment onConvertContent(VoidParams params, @Nullable JugglerFragment fragment) {
        return PreviewFragment.create();
    }

/*    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.createTitleBack();
    }*/

    @Nullable
    @Override
    public String getTitle(Context context, VoidParams params) {
        return "preview";
    }

}
