package me.ilich.juggler.actions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public class CurrentStackPopAction extends StackPopAction {

    @Override
    protected void onExecute(JugglerActivity activity, Juggler juggler, State state, State oldState) {
        juggler.getStacks().popFromCurrentStack();
        State newState = juggler.getStacks().peekCurrentStack();
        if (newState == null) {
            activity.finish();
        } else {
            newState.activate(activity, oldState);
        }
    }

}
