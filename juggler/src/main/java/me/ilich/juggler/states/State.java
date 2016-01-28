package me.ilich.juggler.states;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.ilich.juggler.Event;
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

    public String getTitle() {
        return null;
    }

    @StringRes
    public int getTitleRes() {
        return 0;
    }

    @CallSuper
    public void activate(JugglerActivity activity, State prevState) {
        if (prevState != null) {
            prevState.deactivate(activity);
        }
        String title = getTitle();
        if (TextUtils.isEmpty(title)) {
            int titleRes = getTitleRes();
            if (titleRes == 0) {
                activity.setTitle(null);
            } else {
                activity.setTitle(titleRes);
            }
        } else {
            activity.setTitle(title);
        }
    }

    @CallSuper
    public void deactivate(JugglerActivity activity) {

    }

    public static class Params {

    }

}
