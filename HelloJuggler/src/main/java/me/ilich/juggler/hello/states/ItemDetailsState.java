package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.hello.gui.ItemDetailsFragment;
import me.ilich.juggler.states.GridState;

public class ItemDetailsState extends GridState<ItemDetailsState.Params> {

    public ItemDetailsState(int id) {
        super(Grid.contentOnly(), new Params(id));
    }

    @Override
    @Nullable
    protected Fragment convertCell(Cell cell, Fragment fragment, Params params) {
        final Fragment f;
        switch (cell.getType()) {
            case CONTENT:
                f = ItemDetailsFragment.newInstance(params.id);
                break;
            default:
                f = null;
                break;
        }
        return f;
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

    public static class Params extends GridState.Params {

        private final int id;

        public Params(int id) {
            this.id = id;
        }

    }

}
