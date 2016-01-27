package me.ilich.juggler.actions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public class ResetStacksAction extends StackPushAction {

    @Override
    protected void onExecute(JugglerActivity activity, Juggler juggler, State state) {
        juggler.getStacks().clear();
        juggler.getStacks().pushToCurrentStack(state);
        state.process(activity);
    }

}
