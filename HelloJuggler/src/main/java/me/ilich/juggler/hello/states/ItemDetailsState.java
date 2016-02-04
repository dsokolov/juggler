package me.ilich.juggler.hello.states;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.hello.gui.ItemDetailsFragment;
import me.ilich.juggler.State;

public class ItemDetailsState extends State<ItemDetailsState.Params> {

    public ItemDetailsState(int id) {
        super(Grid.contentOnly(), new Params(id));
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        final JugglerFragment f;
        switch (cellType) {
            case CONTENT:
                f = ItemDetailsFragment.newInstance(params.id);
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

    public static class Params extends State.Params {

        private final int id;

        public Params(int id) {
            this.id = id;
        }

    }

}
