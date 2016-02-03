package me.ilich.juggler.hello.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.ilich.juggler.Event;
import me.ilich.juggler.JugglerFragment;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.grid.CellType;
import me.ilich.juggler.grid.Grid;
import me.ilich.juggler.Transition;
import me.ilich.juggler.hello.R;
import me.ilich.juggler.hello.gui.StandardToolbarFragment;
import me.ilich.juggler.hello.gui.WizardTwoFragment;
import me.ilich.juggler.states.GridState;
import me.ilich.juggler.states.State;

public class WizardTwoState extends GridState<GridState.Params> {

    public WizardTwoState() {
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
                transitions.add(Transition.addCurrentStack(this.getClass(), WizardThreeState.class));
                transitions.add(Transition.backCurrentStack(this.getClass()));
                break;
            default:
                transitions = Collections.emptyList();
                break;
        }
        return transitions;
    }

    @Nullable
    @Override
    protected Fragment convertCell(Cell cell, @Nullable Fragment fragment, @Nullable Params params) {
        final Fragment f;
        switch (cell.getType()) {
            case CONTENT:
                f = new WizardTwoFragment();
                break;
            case TOOLBAR:
                StandardToolbarFragment standardToolbarFragment = StandardToolbarFragment.create();
                standardToolbarFragment.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
                f = standardToolbarFragment;
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

    @Override
    public int getTitleRes() {
        return R.string.title_wizard_two;
    }

    @Override
    protected JugglerFragment onCreateFragment(CellType cellType, Params params) {
        final JugglerFragment f;
        switch (cellType) {
            case CONTENT:
                f = new WizardTwoFragment();
                break;
            case TOOLBAR:
                StandardToolbarFragment standardToolbarFragment = StandardToolbarFragment.create();
                standardToolbarFragment.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
                f = standardToolbarFragment;
                break;
            default:
                f = null;
                break;
        }
        return f;
    }

}
