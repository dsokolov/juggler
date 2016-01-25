package me.ilich.juggler.transitions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public class CurrentStackPopTransition extends StackPopTransition {

    @Override
    protected void onExecute(JugglerActivity activity, Juggler juggler, State state) {
        State newState = juggler.getStacks().prevState();
        if (newState == null) {
            activity.finish();
        } else {
            newState.process(activity);
        }
    }

}
