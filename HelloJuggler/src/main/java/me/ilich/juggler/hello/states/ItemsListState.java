package me.ilich.juggler.hello.states;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.State;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.hello.gui.ItemsListFragment;

public class ItemsListState extends State<State.Params> {

    public ItemsListState() {
        super(Grid.contentOnly(), null);
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        final JugglerFragment f;
        switch (cellType) {
            case CONTENT:
                f = ItemsListFragment.create();
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

}
