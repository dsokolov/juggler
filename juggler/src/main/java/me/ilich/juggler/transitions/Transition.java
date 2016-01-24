package me.ilich.juggler.transitions;

import me.ilich.juggler.Juggler;

public abstract class Transition {

    public void execute(Juggler juggler) {
        onExecute(juggler);
    }

    protected abstract void onExecute(Juggler juggler);


}
