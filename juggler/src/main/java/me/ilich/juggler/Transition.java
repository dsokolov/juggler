package me.ilich.juggler;

import java.io.Serializable;

import androidx.annotation.Nullable;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;

public abstract class Transition implements Serializable {

    public static Transition transaction(String transition, @Nullable String tag) {
        return new Transaction(transition, tag);
    }

    @Nullable
    private final String tag;

    protected Transition(String tag) {
        this.tag = tag;
    }

    public final State execute(JugglerActivity activity, StateChanger stateChanger) {
        return onExecute(activity, stateChanger, tag);
    }

    protected abstract State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag);

    private static class Transaction extends Transition {

        private final String transactionName;

        private Transaction(String transactionName, @Nullable String tag) {
            super(tag);
            this.transactionName = transactionName;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.transaction(transactionName, activity, tag);
        }

    }

}
