package me.ilich.juggler.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import me.ilich.juggler.Grid;
import me.ilich.juggler.JugglerActivity;

public abstract class GridState<P extends State.Params> extends State<P> {

    private final Grid grid;
    @Nullable
    private final P params;

    protected GridState(Grid grid, @Nullable P params) {
        this.grid = grid;
        this.params = params;
    }

    @Override
    public void process(JugglerActivity activity) {
        activity.setContentView(grid.getLayoutId());
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        for (Grid.Cell cell : grid.getCells()) {
            int containerId = cell.getContainerId();
            Fragment oldFragment = activity.getSupportFragmentManager().findFragmentById(containerId);
            Fragment newFragment = convertCell(cell, oldFragment, params);
            if (newFragment != null) {
                fragmentTransaction.replace(containerId, newFragment);
            }
        }
        fragmentTransaction.commit();
    }

    @Nullable
    protected abstract Fragment convertCell(Grid.Cell cell, @Nullable Fragment fragment, @Nullable P params);

}
