package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.ilich.juggler.Grid;
import me.ilich.juggler.states.GridState;

public class CategoriesState extends GridState<GridState.Params> {

    public CategoriesState() {
        super(Grid.contentOnly(), null);
    }

    @Nullable
    @Override
    protected Fragment convertCell(Grid.Cell cell, @Nullable Fragment fragment, Params params) {
        return null;
    }

}
