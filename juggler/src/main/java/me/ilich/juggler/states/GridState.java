package me.ilich.juggler.states;

import android.support.v4.app.Fragment;

import me.ilich.juggler.Grid;
import me.ilich.juggler.JugglerActivity;

public abstract class GridState<P extends State.Params> extends State<P> {

    private final Grid grid;
    private final P params;

    protected GridState(Grid grid, P params) {
        this.grid = grid;
        this.params = params;
    }

    @Override
    public void processActivity(JugglerActivity activity) {
        activity.setContentView(grid.getLayoutId());
        for (Grid.Cell cell : grid.getCells()) {
            convertCell(activity, cell, params);
        }
    }

    protected abstract Fragment convertCell(JugglerActivity activity, Grid.Cell cell, P params);

}
