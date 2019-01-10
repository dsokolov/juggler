package me.ilich.juggler.states;


import androidx.annotation.Nullable;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.gui.JugglerFragment;

public abstract class ContentDoubleToolbarState<P extends State.Params> extends State<P> {

    public ContentDoubleToolbarState(@Nullable P params) {
        super(Grid.contentDoubleToolbar(), params);
    }

    @Override
    protected JugglerFragment onConvertFragment(int cellType, P params, @Nullable JugglerFragment fragment) {
        switch (cellType) {
            case Cell.CELL_TYPE_CONTENT_BELOW:
                fragment = onConvertContentBelow(params, fragment);
                break;
            case Cell.CELL_TYPE_CONTENT_UNDER:
                fragment = onConvertContentUnder(params, fragment);
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

    protected abstract JugglerFragment onConvertContentBelow(P params, @Nullable JugglerFragment fragment);

    protected abstract JugglerFragment onConvertContentUnder(P params, @Nullable JugglerFragment fragment);

    protected abstract JugglerFragment onConvertToolbar(P params, @Nullable JugglerFragment fragment);

}
