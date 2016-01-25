package me.ilich.juggler.transitions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public abstract class Transition {

    public void execute(JugglerActivity activity, Juggler juggler, State state) {
        onExecute(activity, juggler, state);
    }

    protected abstract void onExecute(JugglerActivity activity, Juggler juggler, State state);

}
