package me.ilich.juggler.hello.states;

import java.util.List;

import me.ilich.juggler.Event;
import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.Transition;
import me.ilich.juggler.hello.gui.ItemsListFragment;
import me.ilich.juggler.State;

public class ItemsListState extends State<State.Params> {

    public ItemsListState() {
        super(Grid.contentOnly(), null);
    }

    @Override
    protected List<Transition> createTransitionsForEvent(Event event) {
        final List<Transition> transitions = null;
/*        switch (event) {
            case BACK:
                transitions = Collections.singletonList(Transition.backCurrentStack(this.getClass()));
                break;
            case OTHER:
                transitions = new ArrayList<>();
                transitions.add(Transition.addCurrentStack(this.getClass(), ItemDetailsState.class));
                break;
            default:
                transitions = Collections.emptyList();
                break;
        }*/
        return transitions;
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        final JugglerFragment f;
        switch (cellType){
            case CONTENT:
                f = ItemsListFragment.create();
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

}
