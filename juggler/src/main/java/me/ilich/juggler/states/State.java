package me.ilich.juggler.states;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ilich.juggler.Event;
import me.ilich.juggler.Grid;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.Transition;

public abstract class State<P extends State.Params> {

    @Nullable
    private final P params;
    private final Map<Event, List<Transition>> availableTransitions = new HashMap<>();

    public State(@Nullable P params) {
        this.params = params;
        for (Event event : Event.values()) {
            List<Transition> transitions = createTransitionsForEvent(event);
            availableTransitions.put(event, transitions);
        }
    }

    @Nullable
    protected final P getParams() {
        return params;
    }

    protected List<Transition> createTransitionsForEvent(Event event) {
        return Collections.emptyList();
    }

    public final List<Transition> getTransitions(Event event) {
        final List<Transition> result;
        if (availableTransitions.containsKey(event)) {
            result = availableTransitions.get(event);
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    public abstract void activate(JugglerActivity activity, State prevState);

    public abstract void deactivate(JugglerActivity activity);

    public static class Params {

    }

}
