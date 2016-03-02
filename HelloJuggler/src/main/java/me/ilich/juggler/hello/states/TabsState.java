package me.ilich.juggler.hello.states;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.TabsFragment;
import me.ilich.juggler.states.ContentBelowToolbarState;
import me.ilich.juggler.states.VoidParams;

public class TabsState extends ContentBelowToolbarState<VoidParams> {

    public TabsState() {
        super(null);
    }

    @Override
    protected JugglerFragment onCreateContent(VoidParams params) {
        return TabsFragment.newInstance();
    }

    @Override
    protected JugglerFragment onCreateToolbar(VoidParams params) {
        return StandardToolbarFragment.create();
    }

}
