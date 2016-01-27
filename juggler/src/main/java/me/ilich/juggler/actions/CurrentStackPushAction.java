package me.ilich.juggler.actions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public class CurrentStackPushAction extends StackPushAction {

    @Override
    protected void onExecute(JugglerActivity activity, Juggler juggler, State state, State oldState) {
        juggler.getStacks().pushToCurrentStack(state);
        state.activate(activity, oldState);
    }

}
