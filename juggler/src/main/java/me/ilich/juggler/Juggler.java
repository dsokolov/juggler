package me.ilich.juggler;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.ilich.juggler.states.ClosedSystemState;
import me.ilich.juggler.states.State;

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
    @Nullable
    private State currentState = null;

    private Juggler() {

    }

    public void changeState(State state) {
        JugglerActivity activity = activities.get(activities.size() - 1);
        state.processActivity(activity);
    }

    public void registerTransition(Class<? extends State> source, Class<? extends State> destination, Transition transition) {
        TransitionBundle transitionBundle = new TransitionBundle(source, destination, transition);
        transitionBundles.add(transitionBundle);
    }

    public List<TransitionBundle> getTransitionBundles() {
        List<TransitionBundle> list = new ArrayList<>();
        for (TransitionBundle transitionBundle : transitionBundles) {
            if (transitionBundle.source.equals(ClosedSystemState.class)) {
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

    public static class TransitionBundle {

        private final Class<? extends State> source;
        private final Class<? extends State> destination;
        private final Transition transition;

        public TransitionBundle(Class<? extends State> source, Class<? extends State> destination, Transition transition) {
            this.source = source;
            this.destination = destination;
            this.transition = transition;
        }

    }

}
