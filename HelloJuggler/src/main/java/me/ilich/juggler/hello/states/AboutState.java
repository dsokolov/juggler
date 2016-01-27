package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Collections;
import java.util.List;

import me.ilich.juggler.Event;
import me.ilich.juggler.Grid;
import me.ilich.juggler.Transition;
import me.ilich.juggler.hello.gui.AboutFragment;
import me.ilich.juggler.states.GridState;

public class AboutState extends GridState<GridState.Params> {

    public AboutState() {
        super(Grid.contentOnly(), null);
    }

    @Override
    protected List<Transition> createTransitionsForEvent(Event event) {
        final List<Transition> transitions;
        switch (event) {
            case BACK:
                transitions = Collections.singletonList(Transition.backCurrentStack(this.getClass()));
                break;
            default:
                transitions = Collections.emptyList();
                break;
        }
        return transitions;
    }

    @Nullable
    @Override
    protected Fragment convertCell(Grid.Cell cell, @Nullable Fragment fragment, Params params) {
        final Fragment f;
        switch (cell.getType()) {
            case CONTENT:
                f = AboutFragment.newInstance();
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

}
