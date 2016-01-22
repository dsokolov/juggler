package me.ilich.juggler.transitions;

import me.ilich.juggler.Juggler;

public abstract class Transition {

    public static Transition addToStack(String stackName) {
        return new CurrentStackPushTransition();
    }

    public static Transition clearStackAndAdd(String stackName) {
        return new ClearCurrentStackAndPushTransition();
    }

    public static Transition prevState() {
        return new StackPopTransition();
    }

    public static Transition clearCurrentStack() {
        return new Transition();
    }

    public void execute(Juggler juggler) {
        onExecute(juggler);
    }

    protected abstract void onExecute(Juggler juggler);


}
