package me.ilich.juggler.states;

import me.ilich.juggler.JugglerActivity;

public abstract class State<P extends State.Params> {

    public abstract void process(JugglerActivity activity);

    public static class Params {

    }

}
