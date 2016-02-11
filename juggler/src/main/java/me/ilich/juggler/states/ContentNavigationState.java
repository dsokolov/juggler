package me.ilich.juggler.states;

import android.support.annotation.Nullable;

import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.gui.JugglerFragment;

public abstract class ContentNavigationState<P extends State.Params> extends State<P> {

    public ContentNavigationState(@Nullable P params) {
        super(Grid.contentNavigation(), params);
    }

    @Override
    protected JugglerFragment onCreateFragment(int cellType, P params) {
        final JugglerFragment fragment;
        switch (cellType) {
            case Cell.CELL_TYPE_CONTENT:
                fragment = onCreateContent(params);
                break;
            case Cell.CELL_TYPE_NAVIGATION:
                fragment = onCreateNavigation(params);
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    protected abstract JugglerFragment onCreateContent(P params);

    protected abstract JugglerFragment onCreateNavigation(P params);

}
