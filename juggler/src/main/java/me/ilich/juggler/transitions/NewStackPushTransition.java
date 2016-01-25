package me.ilich.juggler.transitions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public class NewStackPushTransition extends StackPushTransition {

    private final String stackName;

    public NewStackPushTransition(String stackName) {
        this.stackName = stackName;
    }

    @Override
    protected void onExecute(JugglerActivity activity, Juggler juggler, State state) {
        state.process(activity);
        juggler.getStacks().setCurrentStack(stackName);
        juggler.getStacks().addStateToCurrentStack(state);
    }

}
