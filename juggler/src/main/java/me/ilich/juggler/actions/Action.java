package me.ilich.juggler.actions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public abstract class Action {

    public void execute(JugglerActivity activity, Juggler juggler, State<?> newState, State<?> oldState) {
        onExecute(activity, juggler, newState, oldState);
    }

    protected abstract void onExecute(JugglerActivity activity, Juggler juggler, State<?> newState, State<?> oldState);

}
