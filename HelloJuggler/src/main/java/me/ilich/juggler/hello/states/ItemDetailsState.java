package me.ilich.juggler.hello.states;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.hello.gui.ItemDetailsFragment;
import me.ilich.juggler.states.ContentOnlyState;
import me.ilich.juggler.states.State;

public class ItemDetailsState extends ContentOnlyState<ItemDetailsState.Params> {

    public ItemDetailsState(int id) {
        super(new Params(id));
    }

    @Override
    protected JugglerFragment onCreateContent(ItemDetailsState.Params params) {
        return ItemDetailsFragment.newInstance(params.id);
    }

    public static class Params extends State.Params {

        private final int id;

        public Params(int id) {
            this.id = id;
        }

    }

}
