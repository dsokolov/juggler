package me.ilich.juggler;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

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
    public void linearState(State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition;
        if (currentState == null) {
            transition = Transition.clearAdd(state);
        } else {
            transition = Transition.addLinear(state);
        }
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void deeperState(State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition;
        if (currentState == null) {
            transition = Transition.clearAdd(state);
        } else {
            transition = Transition.addDeeper(state);
        }
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void clearState(State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition = Transition.clearAdd(state);
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void restore() {
        JugglerActivity activity = activities.get(activities.size() - 1);
        currentState = stateChanger.restore(activity);
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

}
