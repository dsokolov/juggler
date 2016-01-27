package me.ilich.juggler.actions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public class NewStackPopAction extends StackPopAction {

    @Override
    protected void onExecute(JugglerActivity activity, Juggler juggler, State state, State oldState) {
        State s = juggler.getStacks().peekPrevStack();
        if (s == null) {
            throw new NullPointerException("state");
        }
        s.activate(activity, oldState);
    }

}
