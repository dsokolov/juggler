package me.ilich.juggler.states;

import androidx.annotation.Nullable;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.gui.JugglerFragment;

public abstract class ContentNavigationState<P extends State.Params> extends State<P> {

    public ContentNavigationState(@Nullable P params) {
        super(Grid.contentNavigation(), params);
    }

    @Override
    protected JugglerFragment onConvertFragment(int cellType, P params, @Nullable JugglerFragment fragment) {
        switch (cellType) {
            case Cell.CELL_TYPE_CONTENT:
                fragment = onConvertContent(params, fragment);
                break;
            case Cell.CELL_TYPE_NAVIGATION:
                fragment = onConvertNavigation(params, fragment);
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    protected abstract JugglerFragment onConvertContent(P params, @Nullable JugglerFragment fragment);

    protected abstract JugglerFragment onConvertNavigation(P params, @Nullable JugglerFragment fragment);

}
