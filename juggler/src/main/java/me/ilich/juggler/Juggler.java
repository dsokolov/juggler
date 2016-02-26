package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.ClearPopCondition;
import me.ilich.juggler.change.DeeperAdd;
import me.ilich.juggler.change.DigPop;
import me.ilich.juggler.change.LinearAdd;
import me.ilich.juggler.change.PopCondition;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public class Juggler implements Navigable {

    private static Juggler instance;

    public static void init() {
        instance = new Juggler();
    }

    public static Juggler getInstance() {
        if (instance == null) {
            throw new RuntimeException("Call init() first.");
        }
        return instance;
    }

    private StateChanger stateChanger = new StateChanger();
    @Nullable
    private State currentState = null;
    private List<JugglerActivity> activities = new ArrayList<>();

    private Juggler() {

    }

    @Override
    public boolean backState() {
        final boolean b;
        if (currentState == null) {
            b = false;
        } else {
            Transition transition = currentState.getBackTransition();
            if (transition == null) {
                b = false;
            } else {
                JugglerActivity activity = activities.get(activities.size() - 1);
                currentState = transition.execute(activity, stateChanger);
                b = currentState != null;
            }
        }
        return b;
    }

    @Override
    public boolean upState() {
        final boolean b;
        if (currentState == null) {
            b = false;
        } else {
            Transition transition = currentState.getUpTransition();
            if (transition == null) {
                b = false;
            } else {
                JugglerActivity activity = activities.get(activities.size() - 1);
                currentState = transition.execute(activity, stateChanger);
                b = currentState != null;
            }
        }
        return b;
    }

    @Override
    public void linearState(State state, TargetBound... targetBounds) {
        doState(null, new LinearAdd(state, null, targetBounds));
    }

    @Override
    public void linearState(State state, @Nullable String tag) {
        doState(null, new LinearAdd(state, tag));
    }

    @Override
    public void deeperState(State state, TargetBound... targetBounds) {
        doState(null, new DeeperAdd(state, null, targetBounds));
    }

    @Override
    public void deeperState(State state, String tag) {
        doState(null, new DeeperAdd(state, tag));
    }

    @Override
    public void clearState(State state) {
        doState(new ClearPopCondition(), new DeeperAdd(state, null));
    }

    @Override
    public void clearState(State state, String tag) {
        doState(new ClearPopCondition(), new DeeperAdd(state, tag));
    }

    @Override
    public void dig(String tag) {
        doState(new DigPop(tag), null);
    }

    @Override
    public void digLinearState(String digTag, State state) {
        doState(new DigPop(digTag), new DeeperAdd(state, null));
    }

    @Override
    public void digDeeperState(String tag, State state) {
        doState(new DigPop(tag), new DeeperAdd(state, tag));
    }

    @Override
    public void restore() {
        JugglerActivity activity = activities.get(activities.size() - 1);
        currentState = stateChanger.restore(activity);
    }

    @Override
    public void state(PopCondition popCondition, Add addCondition) {
        doState(popCondition, addCondition);
    }

    private void doState(PopCondition popCondition, Add addCondition) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition = Transition.custom(null, popCondition, addCondition);
        currentState = transition.execute(activity, stateChanger);
    }

    public void registerActivity(JugglerActivity activity) {
        activities.add(activity);
    }

    public void unregisterActivity(JugglerActivity activity) {
        activities.remove(activity);
    }

    public void onPostCreate(Bundle savedInstanceState) {
        if (currentState != null) {
            JugglerActivity activity = activities.get(activities.size() - 1);
            currentState.onPostCreate(activity, savedInstanceState);
        }
    }

    public boolean onBackPressed() {
        final boolean b;
        if (currentState == null) {
            b = false;
        } else {
            JugglerActivity activity = activities.get(activities.size() - 1);
            b = currentState.onBackPressed(activity);
        }
        return b;
    }

}
