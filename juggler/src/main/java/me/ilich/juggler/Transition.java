package me.ilich.juggler;

import android.support.annotation.Nullable;

import me.ilich.juggler.change.Add;
import me.ilich.juggler.change.PopCondition;
import me.ilich.juggler.change.StateChanger;
import me.ilich.juggler.gui.JugglerActivity;
import me.ilich.juggler.states.State;
import me.ilich.juggler.states.TargetBound;

public abstract class Transition {

    public static Transition clearAdd(State newState, @Nullable String tag) {
        return new ClearAdd(newState, tag);
    }

    public static Transition addLinear(State newState, @Nullable String tag, TargetBound... targetBounds) {
        return new AddLinear(newState, tag, targetBounds);
    }

    public static Transition digAddLinear(String digTag, State newState, @Nullable String tag) {
        return new DigAddLinear(digTag, newState, tag);
    }

    public static Transition addDeeper(State newState, @Nullable String tag, TargetBound... targetBounds) {
        return new AddDeeper(newState, tag, targetBounds);
    }

    public static Transition digAddDeeper(String digTag, State newState, @Nullable String tag) {
        return new DigAddDeeper(digTag, newState, tag);
    }

    public static Transition transaction(String transition, @Nullable String tag) {
        return new Transaction(transition, tag);
    }

    public static Transition dig(String tag) {
        return new DigTransition(tag);
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

    private static class ClearAdd extends Transition {

        private final State state;

        private ClearAdd(State state, @Nullable String tag) {
            super(tag);
            this.state = state;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.add(state, activity, StateChanger.Mode.ADD_CLEAR, tag);
        }

    }

    private static class AddLinear extends Transition {

        private final State newState;
        private final TargetBound[] targetBounds;

        private AddLinear(State newState, @Nullable String tag, TargetBound... targetBounds) {
            super(tag);
            this.newState = newState;
            this.targetBounds = targetBounds;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.add(newState, activity, StateChanger.Mode.ADD_LINEAR, tag, targetBounds);
        }

    }

    private static class AddDeeper extends Transition {

        private final State newState;
        private TargetBound[] targetBounds;

        private AddDeeper(State newState, @Nullable String tag, TargetBound... targetBounds) {
            super(tag);
            this.newState = newState;
            this.targetBounds = targetBounds;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.add(newState, activity, StateChanger.Mode.ADD_DEEPER, tag, targetBounds);
        }

    }

    private static class DigAddDeeper extends Transition {

        private final String digTag;
        private final State state;

        private DigAddDeeper(String digTag, State state, @Nullable String tag) {
            super(tag);
            this.digTag = digTag;
            this.state = state;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.digAdd(digTag, activity, StateChanger.Mode.ADD_DEEPER, state, tag);
        }

    }

    private static class DigAddLinear extends Transition {

        private final String digTag;
        private final State state;

        private DigAddLinear(String digTag, State state, @Nullable String tag) {
            super(tag);
            this.digTag = digTag;
            this.state = state;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.digAdd(digTag, activity, StateChanger.Mode.ADD_LINEAR, state, tag);
        }

    }

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

    private static class DigTransition extends Transition {

        protected DigTransition(String tag) {
            super(tag);
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger, String tag) {
            return stateChanger.dig(tag, activity);
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
