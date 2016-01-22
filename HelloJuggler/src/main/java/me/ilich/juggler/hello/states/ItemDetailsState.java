package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import me.ilich.juggler.Grid;
import me.ilich.juggler.hello.ItemDetailsFragment;
import me.ilich.juggler.states.GridState;

public class ItemDetailsState extends GridState<ItemDetailsState.Params> {

    public ItemDetailsState(int id) {
        super(Grid.contentOnly(), new Params(id));
    }

    @Override
    @Nullable
    protected Fragment convertCell(Grid.Cell cell, Fragment fragment, Params params) {
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

    public static class Params extends GridState.Params {

        private final int id;

        public Params(int id) {
            this.id = id;
        }

    }

}
