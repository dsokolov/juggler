package me.ilich.juggler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import me.ilich.juggler.states.InactiveSystemState;
import me.ilich.juggler.states.PreviousStateSystemState;
import me.ilich.juggler.states.State;
import me.ilich.juggler.transitions.NewStackPushTransition;
import me.ilich.juggler.transitions.Transition;

public class Juggler {

    private static Juggler instance;

    public static void init() {
        instance = new Juggler();
    }

    public static Juggler getInstance() {
        return instance;
    }

    private List<JugglerActivity> activities = new ArrayList<>();
    private List<TransitionBundle> transitionBundles = new ArrayList<>();
    private Stacks stacks = new Stacks();
    private State currentState = new InactiveSystemState();

    private Juggler() {

    }

    public void changeState(State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        Transition transition = null;
        for (TransitionBundle transitionBundle : transitionBundles) {
            if (transitionBundle.destination.equals(state.getClass())) {
                transition = transitionBundle.transition;
            }
        }
        if (transition == null) {
            throw new RuntimeException(String.format("Transition from %s to %s in not registered.", currentState.getClass().getName(), state.getClass().getName()));
        } else {
            state.process(activity);
            currentState = state;
        }
    }

    public void registerStartup(Class<? extends State> source, String stackName) {
        TransitionBundle transitionBundle = new TransitionBundle(null, source, new NewStackPushTransition(), Event.STARTUP);
    }

    public void registerTransition(Class<? extends State> source, Class<? extends State> destination, Transition transition) {
        TransitionBundle transitionBundle = new TransitionBundle(source, destination, transition, Event.OTHER);
        transitionBundles.add(transitionBundle);
    }

    public void registerBack(Class<? extends State> source, Transition transition) {
        //TODO
    }

    public void registerUp(Class<? extends State> source, Transition transition) {
        //TODO
    }

    public List<TransitionBundle> getBackTransitionBundles() {
        List<TransitionBundle> list = new ArrayList<>();
        for (TransitionBundle transitionBundle : transitionBundles) {
            boolean isSourceCurrentState = transitionBundle.source.equals(currentState.getClass());
            boolean isDestinationBack = transitionBundle.destination.equals(PreviousStateSystemState.class);
            if (isSourceCurrentState && isDestinationBack) {
                list.add(transitionBundle);
            }
        }
        return list;
    }

    void registerActivity(JugglerActivity activity) {
        activities.add(activity);
    }

    void unregisterActivity(JugglerActivity activity) {
        activities.remove(activity);
    }

    public Class<? extends State> getStartupState() {
        Class<? extends State> r = null;
        for (TransitionBundle transitionBundle : transitionBundles) {
            if (transitionBundle.event == Event.STARTUP) {
                r = transitionBundle.destination;
            }
        }
        return r;
    }

    public static class TransitionBundle {

        private final Class<? extends State> source;
        private final Class<? extends State> destination;
        private final Transition transition;
        private final Event event;

        public TransitionBundle(Class<? extends State> source, Class<? extends State> destination, Transition transition, Event event) {
            this.source = source;
            this.destination = destination;
            this.transition = transition;
            this.event = event;
        }

        public Class<? extends State> getDestination() {
            return destination;
        }

        public Transition getTransition() {
            return transition;
        }

    }

    public static class Stacks {

        private State currentState = null;
        private Stack<State> stateStack = new Stack<>();

        public State pop() {
            State state = stateStack.pop();
            currentState = state;
            return state;
        }

        public State getCurrentState() {
            return currentState;
        }

    }

}
