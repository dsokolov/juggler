package me.ilich.juggler;

public abstract class Transition {

    public static Transition back() {
        return new Back();
    }

    public static Transition up() {
        return new Up();
    }

    public static Transition clearAdd(State newState) {
        return new ClearAdd(newState);
    }

    public static Transition addLinear(State newState) {
        return new AddLinear(newState);
    }

    public static Transition addDeeper(State newState) {
        return new AddDeeper(newState);
    }

    public static Transition transaction(String transition) {
        return new Transaction(transition);
    }

    public final State execute(JugglerActivity activity, StateChanger stateChanger) {
        return onExecute(activity, stateChanger);
    }

    protected abstract State onExecute(JugglerActivity activity, StateChanger stateChanger);

    private static class Back extends Transition {

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger) {
            return stateChanger.back(activity);
        }

    }

    private static class Up extends Transition {

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger) {
            return stateChanger.up(activity);
        }

    }

    private static class ClearAdd extends Transition {

        private final State state;

        private ClearAdd(State state) {
            this.state = state;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger) {
            return stateChanger.add(state, activity, StateChanger.Mode.ADD_CLEAR);
        }

    }

    private static class AddLinear extends Transition {

        private final State newState;

        private AddLinear(State newState) {
            this.newState = newState;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger) {
            return stateChanger.add(newState, activity, StateChanger.Mode.ADD_LINEAR);
        }

    }

    private static class AddDeeper extends Transition {

        private final State newState;

        private AddDeeper(State newState) {
            this.newState = newState;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger) {
            return stateChanger.add(newState, activity, StateChanger.Mode.ADD_DEEPER);
        }

    }

    private static class Transaction extends Transition {

        private final String transactionName;

        private Transaction(String transactionName) {
            this.transactionName = transactionName;
        }

        @Override
        protected State onExecute(JugglerActivity activity, StateChanger stateChanger) {
            return stateChanger.transaction(transactionName, activity);
        }
    }


}
