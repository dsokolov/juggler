package me.ilich.juggler.transitions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public class ClearCurrentStackAndPushTransition extends StackPushTransition {

    private final String stackName;

    public ClearCurrentStackAndPushTransition(String stackName) {
        this.stackName = stackName;
    }

    @Override
    protected void onExecute(JugglerActivity activity, Juggler juggler, State state) {
        juggler.getStacks().clearCurrentStack();
        juggler.getStacks().addStateToCurrentStack(state);
        state.process(activity);
    }

}
