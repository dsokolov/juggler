package me.ilich.juggler.hello.states;

import java.util.Collections;
import java.util.List;

import me.ilich.juggler.Event;
import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.Transition;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.State;

public class MainState extends State<State.Params> {

    public MainState() {
        super(Grid.contentBelowToolbar(), null);
    }

    @Override
    protected List<Transition> createTransitionsForEvent(Event event) {
        final List<Transition> transitions;
        switch (event) {
/*            case BACK:
                transitions = Collections.singletonList(Transition.backCurrentStack(this.getClass()));
                break;
            case OTHER:
                transitions = new ArrayList<>();
                transitions.add(Transition.addCurrentStack(this.getClass(), AboutState.class));
                transitions.add(Transition.addNewStack(this.getClass(), WizardOneState.class));
                break;*/
            default:
                transitions = Collections.emptyList();
                break;
        }
        return transitions;
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
