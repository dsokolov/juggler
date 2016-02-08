package me.ilich.juggler.hello.states;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.State;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;

public class MainState extends State<State.Params> {

    public MainState() {
        super(Grid.contentBelowToolbar(), null);
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        final JugglerFragment f;
        switch (cellType) {
            case CONTENT:
                f = MainFragment.newInstance();
                break;
            case TOOLBAR:
                f = StandardToolbarFragment.create();
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

}
