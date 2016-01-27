package me.ilich.juggler;

import me.ilich.juggler.actions.Action;
import me.ilich.juggler.actions.CurrentStackPopAction;
import me.ilich.juggler.actions.CurrentStackPushAction;
import me.ilich.juggler.actions.NewStackPopAction;
import me.ilich.juggler.actions.NewStackPushAction;
import me.ilich.juggler.states.State;

public class Transition {

    public static Transition backCurrentStack(Class<? extends State> sourceClass) {
        return new Transition(sourceClass, null, null, new CurrentStackPopAction());
    }

    public static Transition backPrevStack(Class<? extends State> sourceClass) {
        return new Transition(sourceClass, null, null, new NewStackPopAction());
    }

    public static Transition addCurrentStack(Class<? extends State> sourceClass, Class<? extends State> destinationClass) {
        return new Transition(sourceClass, destinationClass, null, new CurrentStackPushAction());
    }

    public static Transition addNewStack(Class<? extends State> sourceClass, Class<? extends State> destinationClass) {
        return new Transition(sourceClass, destinationClass, null, new NewStackPushAction());
    }

    private final Class<? extends State> sourceClass;
    private final Class<? extends State> destinationClass;
    private final State destinationInstance;
    private final Action action;

    private Transition(Class<? extends State> sourceClass, Class<? extends State> destinationClass, State destinationInstance, Action action) {
        this.sourceClass = sourceClass;
        this.destinationClass = destinationClass;
        this.destinationInstance = destinationInstance;
        this.action = action;
    }

    public boolean isAccessibleFrom(State state) {
        return state.getClass().equals(destinationClass);
    }

    public Action getAction() {
        return action;
    }

    public Class<? extends State> getDestinationClass() {
        return destinationClass;
    }

    public State getDestinationInstance() {
        return destinationInstance;
    }

    @Override
    public String toString() {
        return sourceClass.getName() + " -> " + destinationClass.getName() + " ( " + action.toString() + " )";
    }

}
