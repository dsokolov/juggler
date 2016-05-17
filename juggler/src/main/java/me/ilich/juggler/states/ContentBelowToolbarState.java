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
    protected JugglerFragment onConvertFragment(int cellType, P params, @Nullable JugglerFragment fragment) {
        switch (cellType) {
            case Cell.CELL_TYPE_CONTENT:
                fragment = onConvertContent(params, fragment);
                break;
            case Cell.CELL_TYPE_TOOLBAR:
                fragment = onConvertToolbar(params, fragment);
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    protected abstract JugglerFragment onConvertContent(P params, @Nullable JugglerFragment fragment);

    protected abstract JugglerFragment onConvertToolbar(P params, @Nullable JugglerFragment fragment);

}
