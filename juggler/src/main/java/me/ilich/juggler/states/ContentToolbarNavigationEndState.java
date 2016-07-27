package me.ilich.juggler.states;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;

import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.gui.JugglerNavigationFragment;

public abstract class ContentToolbarNavigationEndState<P extends State.Params> extends State<P> {

    public ContentToolbarNavigationEndState(@Nullable P params) {
        super(Grid.contentToolbarNavigationEnd(), params);
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
            case Cell.CELL_TYPE_NAVIGATION:
                fragment = onConvertNavigation(params, fragment);
                Bundle b = fragment.getArguments();
                if (b == null) {
                    fragment.setArguments(new Bundle());
                }
                JugglerNavigationFragment.addDrawerGravityToBundle(fragment.getArguments(), Gravity.END);
                break;
            default:
                fragment = null;
                break;
        }
        return fragment;
    }

    protected abstract JugglerFragment onConvertContent(P params, @Nullable JugglerFragment fragment);

    protected abstract JugglerFragment onConvertToolbar(P params, @Nullable JugglerFragment fragment);

    protected abstract JugglerFragment onConvertNavigation(P params, @Nullable JugglerFragment fragment);

}
