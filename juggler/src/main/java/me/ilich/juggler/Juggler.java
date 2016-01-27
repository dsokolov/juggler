package me.ilich.juggler;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.actions.Action;
import me.ilich.juggler.actions.ResetStacksAction;
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

    private List<JugglerActivity> activities = new ArrayList<>();
    private Stacks stacks = new Stacks();

    private Juggler() {

    }

    @Override
    public void backState() {
        State<?> currentState = stacks.peekCurrentStack();
        List<Transition> transitions = currentState.getTransitions(Event.BACK);
        if (transitions.isEmpty()) {
            throw new RuntimeException("Back state for " + currentState + " is not registred");
        }
        Transition transition = transitions.get(0);
        State state = transition.getDestinationInstance();
        Action action = transition.getAction();
        doChangeState(action, state, currentState);
    }

    @Override
    public boolean upState() {
        State<?> currentState = stacks.peekCurrentStack();
        List<Transition> transitions = currentState.getTransitions(Event.UP);
        if (transitions.isEmpty()) {
            throw new RuntimeException("Up state for " + currentState + " is not registred");
        }
        Transition transition = transitions.get(0);
        State state = transition.getDestinationInstance();
        Action action = transition.getAction();
        doChangeState(action, state, currentState);
        return true;
    }

    @Override
    public void changeState(State state) {
        State<?> currentState = stacks.peekCurrentStack();
        final Action action;
        if (currentState == null) {
            action = new ResetStacksAction();
        } else {
            List<Transition> transitions = currentState.getTransitions(Event.OTHER);
            if (transitions.size() == 0) {
                throw new RuntimeException("No transition from " + currentState + " to " + state + " is not registered");
            }
            Transition transition = null;
            for (Transition tr : transitions) {
                if (tr.isAccessibleFrom(state)) {
                    transition = tr;
                    break;
                }
            }
            if (transition == null) {
                throw new RuntimeException("No transition from " + currentState + " to " + state + " is not registered");
            }
            action = transition.getAction();
        }
        doChangeState(action, state, currentState);
    }

    @Override
    public void currentState() {
        JugglerActivity activity = activities.get(activities.size() - 1);
        State currentState = stacks.peekCurrentStack();
        currentState.activate(activity, currentState);
    }

    private void doChangeState(Action action, State state, State oldState) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        action.execute(activity, this, state, oldState);
    }

    void registerActivity(JugglerActivity activity) {
        activities.add(activity);
    }

    void unregisterActivity(JugglerActivity activity) {
        activities.remove(activity);
    }

    public Stacks getStacks() {
        return stacks;
    }

    public void onSaveInstanceState(Bundle outState) {

    }

}
