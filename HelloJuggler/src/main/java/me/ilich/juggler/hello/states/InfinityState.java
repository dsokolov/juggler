package me.ilich.juggler.hello.states;

import android.content.Context;
import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.fragments.InfinityFragment;
import me.ilich.juggler.hello.gui.fragments.StandardToolbarFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.State;

public class InfinityState extends ContentBelowToolbarState<InfinityState.Params> {

    public InfinityState(int i) {
        super(new Params(i));
    }

    @Override
    protected JugglerFragment onCreateContent(Params params) {
        return InfinityFragment.create(params.i);
    }

    @Override
    protected JugglerFragment onCreateToolbar(Params params) {
        return StandardToolbarFragment.createTitleBack();
    }

    @Nullable
    @Override
    public String getTitle(Context context, Params params) {
        return Integer.toString(params.i);
    }

    public static class Params extends State.Params {

        private final int i;

        public Params(int i) {
            this.i = i;
        }

    }

}
