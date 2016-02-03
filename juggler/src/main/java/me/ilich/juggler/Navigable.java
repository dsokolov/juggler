package me.ilich.juggler;

import me.ilich.juggler.states.State;

public interface Navigable {

    boolean backState();

    boolean upState();

    void changeState(State state);

    void currentState();

}
