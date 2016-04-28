package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.Remove;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.grid.Cell;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.gui.JugglerFragment;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public class Juggler implements Navigable, Serializable {

    public static final String TAG = "Juggler";

    private StateChanger stateChanger = new StateChanger();
    @Nullable
    private State currentState = null;
    private transient JugglerActivity activity;
    private Map<Integer, Boolean> newStateStarted = new HashMap<>();

    @Override
    public boolean backState() {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        final boolean b;
        if (currentState == null) {
            b = false;
        } else {
            Transition transition = currentState.getBackTransition();
            if (transition == null) {
                b = false;
            } else {
                currentState = transition.execute(activity, stateChanger);
                b = currentState != null;
            }
        }
        return b;
    }

    @Override
    public boolean upState() {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        final boolean b;
        if (currentState == null) {
            b = false;
        } else {
            Transition transition = currentState.getUpTransition();
            if (transition == null) {
                b = false;
            } else {
                currentState = transition.execute(activity, stateChanger);
                b = currentState != null;
            }
        }
        return b;
    }

    @Override
    public void linearState(State state, TargetBound... targetBounds) {
        doState(null, Add.linear(state, targetBounds));
    }

    @Override
    public void linearState(State state, @Nullable String tag) {
        doState(null, Add.linear(state, tag));
    }

    @Override
    public void deeperState(State state, TargetBound... targetBounds) {
        doState(null, Add.deeper(state, targetBounds));
    }

    @Override
    public void deeperState(State state, String tag) {
        doState(null, Add.deeper(state, tag));
    }

    @Override
    public void clearState(State state) {
        doState(Remove.clear(), Add.deeper(state));
    }

    @Override
    public void clearState(State state, String tag) {
        doState(Remove.clear(), Add.deeper(state, tag));
    }

    @Override
    public void dig(String tag) {
        doState(Remove.dig(tag), null);
    }

    @Override
    public void digLinearState(String digTag, State state) {
        doState(Remove.dig(digTag), Add.deeper(state));
    }

    @Override
    public void digDeeperState(String tag, State state) {
        doState(Remove.dig(tag), Add.deeper(state, tag));
    }

    @Override
    public void restore() {
        currentState = stateChanger.restore(activity);
    }

    @Override
    public void state(@NonNull Remove.Interface pop) {
        doState(pop, null);
    }

    @Override
    public void state(@NonNull Add.Interface add) {
        doState(null, add);
    }

    @Override
    public void state(@NonNull Remove.Interface pop, @NonNull Add.Interface add) {
        doState(pop, add);
    }

    private void doState(@Nullable Remove.Interface pop, @Nullable Add.Interface add) {
        final Transition transition = Transition.custom(null, pop, add);
        currentState = transition.execute(activity, stateChanger);
        newStateStarted.clear();
        if (currentState != null) {
            for (Cell cell : currentState.getGrid().getCells()) {
                newStateStarted.put(cell.getType(), false);
            }
        }
    }

    public void setActivity(JugglerActivity activity) {
        this.activity = activity;
    }

    public void onPostCreate(Bundle savedInstanceState) {
        if (currentState != null) {
            currentState.onPostCreate(activity, savedInstanceState);
        }
    }

    /**
     * @return true if current state process back press
     * false if not
     */
    public boolean onBackPressed() {
        final boolean b;
        if (currentState == null) {
            b = false;
        } else {
            b = currentState.onBackPressed(activity);
        }
        return b;
    }

    public boolean onUpPressed() {
        final boolean b;
        if (currentState == null) {
            b = false;
        } else {
            JugglerActivity activity = activities.get(activities.size() - 1);
            b = currentState.onUpPressed(activity);
        }
        return b;
    }

    @VisibleForTesting
    public int getStackLength() {
        return stateChanger.getStackLength();
    }

    public void onFragmentStart(JugglerFragment jugglerFragment) {
        if (activity == null) {
            throw new NullPointerException("activity == null");
        }
        int cellType = jugglerFragment.getTargetCell();
        newStateStarted.put(cellType, true);
        boolean allCellAttached = true;
        for (Boolean bool : newStateStarted.values()) {
            if (!bool) {
                allCellAttached = false;
                break;
            }
        }
        if (allCellAttached) {
            if (currentState != null) {
                currentState.onActivate(activity);
            }
        }
    }

    public void onFragmentStop(JugglerFragment jugglerFragment) {

    }

}
