package me.ilich.juggler.hello.states;

import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.State;

public class CategoriesState extends State<State.Params> {

    public CategoriesState() {
        super(Grid.contentOnly(), null);
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        return null;
    }

}
