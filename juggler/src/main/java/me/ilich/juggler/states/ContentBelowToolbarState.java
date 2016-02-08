package me.ilich.juggler.states;

import android.support.annotation.Nullable;

import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.Grid;

public abstract class ContentBelowToolbarState<P extends State.Params> extends State<P> {

    public ContentBelowToolbarState(@Nullable P params) {
        super(Grid.contentBelowToolbar(), params);
    }

    @Override
    protected JugglerFragment onCreateFragment(int cellType, P params) {
        final JugglerFragment fragment;
        switch (cellType) {
            case Cell.CELL_TYPE_CONTENT:
                fragment = onCreateContent(params);
                break;
            case Cell.CELL_TYPE_TOOLBAR:
                fragment = onCreateToolbar(params);
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    protected abstract JugglerFragment onCreateContent(P params);

    protected abstract JugglerFragment onCreateToolbar(P params);

}
