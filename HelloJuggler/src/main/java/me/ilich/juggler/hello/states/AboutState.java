package me.ilich.juggler.hello.states;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import me.ilich.juggler.Grid;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.hello.AboutFragment;
import me.ilich.juggler.states.GridState;

public class AboutState extends GridState<GridState.Params> {

    public AboutState() {
        super(Grid.contentOnly(), null);
    }

    @Override
    protected Fragment convertCell(JugglerActivity activity, Grid.Cell cell, GridState.Params params) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        switch (cell.getType()) {
            case CONTENT:
                Fragment f = AboutFragment.newInstance();
                fragmentTransaction.replace(cell.getContainerId(), f);
                break;
        }
        fragmentTransaction.commit();
        return null;
    }
}
