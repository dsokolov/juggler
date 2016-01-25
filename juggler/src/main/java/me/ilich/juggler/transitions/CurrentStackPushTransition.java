package me.ilich.juggler.transitions;

import me.ilich.juggler.Juggler;
import me.ilich.juggler.JugglerActivity;
import me.ilich.juggler.states.State;

public class CurrentStackPushTransition extends StackPushTransition {

    @Override
    protected void onExecute(JugglerActivity activity, Juggler juggler, State state) {
        juggler.getStacks().addStateToCurrentStack(state);
        state.process(activity);
    }

}
