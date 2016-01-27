package me.ilich.juggler.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

import me.ilich.juggler.Grid;
import me.ilich.juggler.JugglerActivity;

public abstract class GridState<P extends State.Params> extends State<P> {

    private final Map<Grid.Cell.TYPE, Fragment.SavedState> savedStateMap = new HashMap<>();
    private final Grid grid;

    public GridState(Grid grid, @Nullable P params) {
        super(params);
        this.grid = grid;
    }

    @Override
    public void activate(JugglerActivity activity, State prevState) {
        if (prevState != null) {
            prevState.deactivate(activity);
        }
        activity.setContentView(grid.getLayoutId());
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        for (Grid.Cell cell : grid.getCells()) {
            final int containerId = cell.getContainerId();
            Fragment oldFragment = activity.getSupportFragmentManager().findFragmentById(containerId);
            Fragment newFragment = convertCell(cell, oldFragment, getParams());
            if (newFragment != null) {
                if (savedStateMap.containsKey(cell.getType())) {
                    Fragment.SavedState savedState = savedStateMap.get(cell.getType());
                    newFragment.setInitialSavedState(savedState);
                }
                fragmentTransaction.replace(containerId, newFragment);
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    public void deactivate(JugglerActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        for (Grid.Cell cell : grid.getCells()) {
            final int containerId = cell.getContainerId();
            Fragment fragment = fragmentManager.findFragmentById(containerId);
            Fragment.SavedState savedState = fragmentManager.saveFragmentInstanceState(fragment);
            savedStateMap.put(cell.getType(), savedState);
        }
    }

    @Nullable
    protected abstract Fragment convertCell(Grid.Cell cell, @Nullable Fragment fragment, @Nullable P params);

}
