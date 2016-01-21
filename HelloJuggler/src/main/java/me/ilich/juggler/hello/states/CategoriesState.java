package me.ilich.juggler.hello.states;

import android.support.v4.app.Fragment;

import me.ilich.juggler.Grid;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.GridState;

public class CategoriesState extends GridState<GridState.Params> {

    public CategoriesState() {
        super(Grid.contentOnly(), null);
    }

    @Override
    protected Fragment convertCell(JugglerActivity activity, Grid.Cell cell, Params params) {
        return null;
    }

}
