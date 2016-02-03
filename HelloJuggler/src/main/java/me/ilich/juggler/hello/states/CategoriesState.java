package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.states.GridState;
import me.ilich.juggler.states.State;

public class CategoriesState extends GridState<GridState.Params> {

    public CategoriesState() {
        super(Grid.contentOnly(), null);
    }

    @Nullable
    @Override
    protected Fragment convertCell(Cell cell, @Nullable Fragment fragment, Params params) {
        return null;
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        return null;
    }
}
