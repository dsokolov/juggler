package me.ilich.juggler;

import android.support.annotation.Nullable;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.PopCondition;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public abstract class Transition {

    public static Transition transaction(String transition, @Nullable String tag) {
        return new Transaction(transition, tag);
    }

    public static Transition custom(String tag, PopCondition popCondition, Add addCondition) {
        return new CustomTransition(tag, popCondition, addCondition);
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

    private static class CustomTransition extends Transition {

        private final PopCondition popCondition;
        private final Add addCondition;

        protected CustomTransition(String tag, PopCondition popCondition, Add addCondition) {
            super(tag);
            this.popCondition = popCondition;
            this.addCondition = addCondition;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.change(activity, popCondition, addCondition);
        }

    }


}
