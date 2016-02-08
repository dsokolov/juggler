package me.ilich.juggler;

import me.ilich.juggler.states.State;

public interface Navigable {

    boolean backState();

    boolean upState();

    void linearState(State state);

    void deeperState(State state);

    void clearState(State state);

    void restore();

}
