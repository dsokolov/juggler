package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.ilich.juggler.Grid;
import me.ilich.juggler.hello.MainFragment;
import me.ilich.juggler.states.GridState;

public class MainState extends GridState<GridState.Params> {

    public MainState() {
        super(Grid.contentOnly(), null);
    }

    @Nullable
    @Override
    protected Fragment convertCell(Grid.Cell cell, @Nullable Fragment fragment, Params params) {
        final Fragment f;
        switch (cell.getType()) {
            case CONTENT:
                f = MainFragment.newInstance();
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

}
