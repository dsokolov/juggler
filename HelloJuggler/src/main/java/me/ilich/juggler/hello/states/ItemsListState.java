package me.ilich.juggler.hello.states;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.ItemsListFragment;
import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.State;

public class ItemsListState extends ContentOnlyState<State.Params> {

    public ItemsListState() {
        super(null);
    }

    @Override
    protected JugglerFragment onCreateContent(Params params) {
        return ItemsListFragment.create();
    }

}
