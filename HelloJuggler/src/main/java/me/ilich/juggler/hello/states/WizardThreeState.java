package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ilich.juggler.Event;
import me.ilich.juggler.Grid;
import me.ilich.juggler.Transition;
import me.ilich.juggler.hello.gui.WizardThreeFragment;
import me.ilich.juggler.states.GridState;

public class WizardThreeState extends GridState<GridState.Params> {

    public WizardThreeState() {
        super(Grid.contentOnly(), null);
    }

    @Override
    protected List<Transition> createTransitionsForEvent(Event event) {
        final List<Transition> transitions;
        switch (event) {
            case BACK:
                transitions = Collections.singletonList(Transition.backPrevStack(this.getClass()));
                break;
            case OTHER:
                transitions = new ArrayList<>();
                transitions.add(Transition.backPrevStack(this.getClass(), MainState.class));
                transitions.add(Transition.backCurrentStack(this.getClass(), WizardTwoState.class));
                break;
            default:
                transitions = Collections.emptyList();
                break;
        }
        return transitions;
    }

    @Nullable
    @Override
    protected Fragment convertCell(Grid.Cell cell, @Nullable Fragment fragment, @Nullable Params params) {
        final Fragment f;
        switch (cell.getType()) {
            case CONTENT:
                f = new WizardThreeFragment();
                break;
            default:
                f = null;
                break;
        }
        return f;
    }
}
