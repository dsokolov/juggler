package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ilich.juggler.Event;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.Transition;
import me.ilich.juggler.hello.gui.MainFragment;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.states.GridState;

public class MainState extends GridState<GridState.Params> {

    public MainState() {
        super(Grid.contentBelowToolbar(), null);
    }

    @Override
    protected List<Transition> createTransitionsForEvent(Event event) {
        final List<Transition> transitions;
        switch (event) {
            case BACK:
                transitions = Collections.singletonList(Transition.backCurrentStack(this.getClass()));
                break;
            case OTHER:
                transitions = new ArrayList<>();
                transitions.add(Transition.addCurrentStack(this.getClass(), AboutState.class));
                transitions.add(Transition.addNewStack(this.getClass(), WizardOneState.class));
                break;
            default:
                transitions = Collections.emptyList();
                break;
        }
        return transitions;
    }

    @Nullable
    @Override
    protected Fragment convertCell(Cell cell, @Nullable Fragment fragment, Params params) {
        final Fragment f;
        switch (cell.getType()) {
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
