package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.ilich.juggler.Grid;
import me.ilich.juggler.hello.ItemsListFragment;
import me.ilich.juggler.states.GridState;

public class ItemsListState extends GridState<GridState.Params> {

    public ItemsListState() {
        super(Grid.contentOnly(), null);
    }

    @Nullable
    @Override
    protected Fragment convertCell(Grid.Cell cell, @Nullable Fragment fragment, @Nullable Params params) {
        final Fragment f;
        switch (cell.getType()){
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
