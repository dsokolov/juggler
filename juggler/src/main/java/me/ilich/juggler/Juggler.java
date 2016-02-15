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
        doLinerState(state, null);
    }

    @Override
    public void linearState(State state, @Nullable String tag) {
        doLinerState(state, tag);
    }

    private void doLinerState(State state, @Nullable String tag) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition;
        if (currentState == null) {
            transition = Transition.clearAdd(state, tag);
        } else {
            transition = Transition.addLinear(state, tag);
        }
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void deeperState(State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition;
        if (currentState == null) {
            transition = Transition.clearAdd(state, null);
        } else {
            transition = Transition.addDeeper(state, null);
        }
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void deeperState(State state, String tag) {

    }

    private void doDeeperState(State state, @Nullable String tag) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition;
        if (currentState == null) {
            transition = Transition.clearAdd(state, tag);
        } else {
            transition = Transition.addDeeper(state, tag);
        }
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void clearState(State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition = Transition.clearAdd(state, null);
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void clearState(State state, String tag) {

    }

    @Override
    public void dig(String tag) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition = Transition.dig(tag);
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void digLinearState(String digTag, State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition;
        if (currentState == null) {
            transition = Transition.clearAdd(state, null);
        } else {
            transition = Transition.digAddLinear(digTag, state, null);
        }
        currentState = transition.execute(activity, stateChanger);
    }

    @Override
    public void digDeeperState(String tag, State state) {
        doDigDeeperState(tag, state);
    }

    private void doDigDeeperState(String digTag, State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        final Transition transition;
        if (currentState == null) {
            transition = Transition.clearAdd(state, null);
        } else {
            transition = Transition.digAddDeeper(digTag, state, null);
        }
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
