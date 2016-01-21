package me.ilich.juggler.hello.states;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import me.ilich.juggler.Grid;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.hello.ItemDetailsFragment;
import me.ilich.juggler.states.GridState;

public class ItemDetailsState extends GridState<ItemDetailsState.Params> {

    public ItemDetailsState(int id) {
        super(Grid.contentOnly(), new Params(id));
    }

    @Override
    protected Fragment convertCell(JugglerActivity activity, Grid.Cell cell, Params params) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        switch (cell.getType()) {
            case CONTENT:
                Fragment fragment = ItemDetailsFragment.newInstance(params.id);
                fragmentTransaction.replace(cell.getContainerId(), fragment);
                break;
        }
        fragmentTransaction.commit();
        return null;
    }

    public static class Params extends GridState.Params {

        private final int id;

        public Params(int id) {
            this.id = id;
        }

    }

}
